//package com.cpms.framework.log.handler;
//
//import com.cpms.common.utils.CsPropsUtil;
//import com.cpms.common.utils.CsSecureUtil;
//import com.cpms.common.utils.CsWebUtil;
//import com.cpms.framework.log.annotations.OperLog;
//import com.google.common.collect.Maps;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StreamUtils;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//import java.lang.reflect.Method;
//import java.util.Map;
//import java.util.Objects;
//
//
///**
// * @description: 请求拦截器
// * @author: gulang
// * @time: 2021/7/27 19:21
// */
//@Component
//public class RequestHandlerInterceptor implements HandlerInterceptor {
//    private static final String METHOD_GET = "GET";
//    /**
//     * 请求开始时间标识
//     */
//    private static final String LOGGER_SEND_TIME = "_send_time";
//
//    /**
//     * 请求之前处理
//     * @param request
//     * @param response
//     * @param o
//     * @return
//     */
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws IOException {
//        //获取请求body
//
//        //设置请求开始时间
//        request.setAttribute(LOGGER_SEND_TIME,System.currentTimeMillis());
//        return  true;
//    }
//
//    /**
//     * 请求方法结束之后处理
//     * @param request
//     * @param response
//     * @param handler
//     * @param ex
//     */
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws IOException {
//        System.out.println("-------拦截器记录操作日志-------");
//        getArgs(request);
//    }
//
//    /**
//     * 构建使用@OperLog注解的操作日志信息
//     * @param request
//     */
//    private  void builderOperLog(HttpServletRequest request,Object handler) throws IOException {
//        HandlerMethod handlerMethod = (HandlerMethod) handler;
//        Method method = handlerMethod.getMethod();
//        OperLog operLog = method.getAnnotation(OperLog.class);
//        if (Objects.nonNull(operLog)) {
//            Map<String,Object> operLogMsg = Maps.newHashMap();
//            operLogMsg.put("title",operLog.desc());
//            operLogMsg.put("serviceName", CsPropsUtil.getProperty("spring.application.name"));
//            operLogMsg.put("handleIp", CsWebUtil.getIpAddr());
//            operLogMsg.put("reqUrl",request.getRequestURI());
//            operLogMsg.put("reqMethod", request.getMethod().toUpperCase());
//            operLogMsg.put("reqParams",getArgs(request));
//            operLogMsg.put("createBy", CsSecureUtil.userAccount());
//            //结束时间
//            long endTime = System.currentTimeMillis();
//            //请求开始时间
//            long time = Long.parseLong(request.getAttribute(LOGGER_SEND_TIME).toString());
//            operLogMsg.put("exeTime", (endTime - time));
//            System.out.println(operLogMsg);
//        }
//    }
//
//    /**
//     * 获取请求入参
//     * @param request
//     * @return
//     */
//    private String getArgs(HttpServletRequest request) throws IOException {
//        String param ="";
//        String method = request.getMethod().toUpperCase();
//
//        if(Objects.equals(METHOD_GET,method)) {
//            param = request.getQueryString();
//        }
//        return  param;
//    }
//}
