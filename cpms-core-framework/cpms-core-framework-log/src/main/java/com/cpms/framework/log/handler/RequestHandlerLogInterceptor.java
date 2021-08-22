package com.cpms.framework.log.handler;

import com.alibaba.fastjson.JSON;
import com.cpms.common.core.api.Result;
import com.cpms.common.utils.CsPropsUtil;
import com.cpms.common.utils.CsSecureUtil;
import com.cpms.common.utils.CsSpringUtil;
import com.cpms.common.utils.CsWebUtil;
import com.cpms.framework.log.annotations.OperLog;
import com.cpms.framework.log.dto.LogDTO;
import com.cpms.framework.log.event.LogEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Objects;


/**
 * @description: 请求拦截器，和过滤器不同的是拦截器可以知道当前的用户请求的是哪个控制器的哪个方法，所以功能更加强大
 * @author: gulang
 * @time: 2021/7/27 19:21
 */
public class RequestHandlerLogInterceptor implements HandlerInterceptor {
    private static final String METHOD_GET = "GET";
    public static final String FILTER = "filter";
    private final Logger filterLog = LoggerFactory.getLogger(FILTER);
    /**
     * 请求开始时间标识
     */
    private static final String LOGGER_SEND_TIME = "_send_time";

    /**
     * 请求日志实体类
     */
    private static final String LOGGER_ENTITY = "_logger_entity";

    /**
     * 请求之前处理,所有需要从request头获取信息的操作都放在这步进行，这样拿到的信息是最原始的
     * @param request
     * @param response
     * @return
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        System.out.println("请求拦截开始.........");
        //设置请求开始时间
        request.setAttribute(LOGGER_SEND_TIME,System.currentTimeMillis());
        //获取控制器名
        String controllerName = ((HandlerMethod) handler).getBean().getClass().getTypeName();
        //获取方法名
        String methodName = ((HandlerMethod) handler).getMethod().getName();
        LogDTO logDTO = new LogDTO();
        logDTO.setServiceName((String)CsPropsUtil.getProperty("spring.application.name"));
        logDTO.setHandleIp(CsWebUtil.getIpAddr());
        logDTO.setReqMethod(request.getMethod().toUpperCase());
        logDTO.setReqParams(getArgs(request));
        logDTO.setControllerName(controllerName);
        logDTO.setMethodName(methodName);
        logDTO.setReqUrl(request.getRequestURI());
        request.setAttribute(LOGGER_ENTITY,logDTO);
        return  true;
    }

    /**
     * 请求方法结束之后处理
     * @param request
     * @param response
     * @param handler
     * @param ex
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        System.out.println("请求拦截结束..........");
        LogDTO logDTO = (LogDTO)request.getAttribute(LOGGER_ENTITY);
        String resultJson = String.valueOf(request.getAttribute(ResponseIntercept.RESPONSE_JSON));
        Result resultVO = JSON.parseObject(resultJson, Result.class);
        resultVO.setObj(null);
        logDTO.setResultMsg(JSON.toJSONString(resultVO));
        StringBuilder builderFilterLog = new StringBuilder();
        //结束时间
        long endTime = System.currentTimeMillis();
        //请求开始时间
        long startTime = Long.parseLong(request.getAttribute(LOGGER_SEND_TIME).toString());
        long exeTime = endTime - startTime;
        logDTO.setExeTime(exeTime);
        builderFilterLog(logDTO,builderFilterLog);
        // 操作日志记录 异步执行
        builderOperLog(handler,logDTO);
    }

    /**
     * 构建filter日志信息
     * @return
     */
    private  void builderFilterLog(LogDTO logDTO,StringBuilder builderLog){
        builderLog.append("【请求日志】-> ");
        builderLog.append("接口URI: ").append(logDTO.getReqUrl());
        builderLog.append("，请求方式 : ").append(logDTO.getReqMethod());
        builderLog.append("，控制器名称 : ").append(logDTO.getControllerName());
        builderLog.append("，方法名称 : ").append(logDTO.getMethodName());
        builderLog.append("，入参 : ").append(logDTO.getReqParams());
        builderLog.append("，响应结果=").append(logDTO.getResultMsg());
        builderLog.append("，耗时：").append(logDTO.getExeTime()).append("ms");
        filterLog.info(builderLog.toString());
    }

    /**
     * 构建使用@OperLog注解的操作日志信息
     */
    private  void builderOperLog(Object handler,LogDTO logDTO){
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        OperLog operLog = method.getAnnotation(OperLog.class);
        if (Objects.nonNull(operLog)) {
            logDTO.setTitle(operLog.desc());
            logDTO.setCreateBy(CsSecureUtil.userAccount());
            // 发布操作日志事件，异步执行
            CsSpringUtil.publishEvent(new LogEvent(this,logDTO));
        }
    }

    /**
     * 获取请求入参
     * @param request
     * @return
     */
    private String getArgs(HttpServletRequest request) throws IOException {
        String param ="";
        param = request.getQueryString();
        return  param;
    }
}
