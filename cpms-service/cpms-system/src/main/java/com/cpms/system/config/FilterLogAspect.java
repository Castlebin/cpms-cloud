package com.cpms.system.config;

import com.alibaba.fastjson.JSON;
import com.cpms.common.core.api.Result;
import com.cpms.common.utils.CsWebUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @description: filter级别日志切面，记录调用接口的入参
 * @author: gulang
 * @time: 2021/8/12 16:14
 */
@Aspect
@Component
public class FilterLogAspect{
    /**
     * 将controller层的接口入参日志打印在filter文件中
     * 过滤器
     */
    public static final String FILTER = "filter";
    private final Logger filterLog = LoggerFactory.getLogger(FILTER);
    private final Logger logger = LoggerFactory.getLogger(FilterLogAspect.class);

    @Pointcut(value = "execution(* com.cpms.system.modouls.sys.controller..*.*(..))")
    public void filterLogAspect (){}

    @Around("filterLogAspect()")
    @SuppressWarnings("all")
    public Object handelAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        // 我们从请求的上下文中获取request，记录请求的内容
        HttpServletRequest request = CsWebUtil.getRequest();
        String builderLog = builderFilterLog(request, joinPoint);
        filterLog.info("【接口请求入参】,{}，",builderLog);
        System.out.println("---filterLogAspect start---");
        Object result =  joinPoint.proceed();
        System.out.println("---filterLogAspect end---");
        // 日志obj不记录
        Result resultVO = JSON.parseObject(JSON.toJSONString(result), Result.class);
        resultVO.setObj(null);
        long end = System.currentTimeMillis();
        logger.info("【响应日志】,{}，响应结果={}，耗时：{}ms",builderLog,resultVO.toString(),(end - start));
        return result;
    }

    private  String builderFilterLog(HttpServletRequest request,ProceedingJoinPoint joinPoint){
        StringBuilder builderLog = new StringBuilder();
        String method = request.getMethod().toUpperCase();
        builderLog.append("请求URL : ").append(request.getRequestURL().toString());
        if(Objects.equals("GET",method)) {
            // 获取get请求的参数
            String param = request.getQueryString();
            if(!StringUtils.isBlank(param)) {
                builderLog.append("?").append(param);
            }
        }
        builderLog.append("，请求方式 : ").append(method);
        builderLog.append("，入参 : ").append(JSON.toJSONString(joinPoint.getArgs()));
        return builderLog.toString();
    }
}
