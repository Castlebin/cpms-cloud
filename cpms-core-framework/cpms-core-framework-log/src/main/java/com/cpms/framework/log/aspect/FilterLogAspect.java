//package com.cpms.framework.log.aspect;
//
//import com.alibaba.fastjson.JSON;
//import com.cpms.common.core.api.Result;
//import com.cpms.common.utils.CsPropsUtil;
//import com.cpms.common.utils.CsSecureUtil;
//import com.cpms.common.utils.CsSpringUtil;
//import com.cpms.common.utils.CsWebUtil;
//import com.cpms.framework.log.annotations.OperLog;
//import com.cpms.framework.log.dto.LogDTO;
//import com.cpms.framework.log.event.LogEvent;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.Configuration;
//import javax.servlet.http.HttpServletRequest;
//import java.lang.reflect.Method;
//import java.util.Objects;
//
///**
// * @description: filter级别日志切面，记录调用接口的入参以及操作日志记录
// * @author: gulang
// * @time: 2021/8/12 16:14
// */
//@Aspect
//@Configuration
//public class FilterLogAspect{
//    /**
//     * 将controller层的接口入参日志打印在filter文件中
//     * 过滤器
//     */
//    public static final String FILTER = "filter";
//    public static final String METHOD_GET = "GET";
//    private final Logger filterLog = LoggerFactory.getLogger(FILTER);
//
//    /**
//     * execution（）	表达式的主体；
//     * 第一个”*“符号：表示返回值的类型任意；
//     * ”..“：表示当前包及子孙包
//     * ”..*“：表示当前包及子孙包所有的类
//     * .*(..)：表示类中任何方法名，括号表示参数，两个点表示任何参数类型
//     *
//     *  !Note:服务内部通过feign远程调用接口时也会被拦截
//     */
//    @Pointcut(value = "execution(* com.cpms..controller..*.*(..)) || execution(* com.cpms..feign..*.*(..))")
//    public void filterLogAspect (){}
//
//    @Around("filterLogAspect()")
//    @SuppressWarnings("all")
//    public Object handelAround(ProceedingJoinPoint joinPoint) throws Throwable {
//        System.out.println("AOP拦截开始..........");
//        long start = System.currentTimeMillis();
//        // 我们从请求的上下文中获取request，记录请求的内容
//        HttpServletRequest request = CsWebUtil.getRequest();
//        String args = getArgs(request,joinPoint);
//        String builderFilterLog = builderFilterLog(request, joinPoint,args);
//        Object result =  joinPoint.proceed();
//        // 日志obj不记录
//        Result resultVO = JSON.parseObject(JSON.toJSONString(result), Result.class);
//        resultVO.setObj(null);
//        long end = System.currentTimeMillis();
//        filterLog.info("【请求日志】-> {}，响应结果={}，耗时：{}ms",builderFilterLog,JSON.toJSONString(resultVO),(end - start));
//        //记录操作日志
//        builderOperLog(request, joinPoint,(end - start),args,JSON.toJSONString(resultVO));
//        System.out.println("AOP拦截结束..........");
//        return result;
//    }
//
//    /**
//     * 构建filter日志信息
//     * @param request
//     * @param joinPoint
//     * @return
//     */
//    private  String builderFilterLog(HttpServletRequest request,ProceedingJoinPoint joinPoint,String args){
//        StringBuilder builderLog = new StringBuilder();
//        String method = request.getMethod().toUpperCase();
//        builderLog.append("接口URI: ").append(request.getRequestURI());
//        builderLog.append("，访问方法 : ").append(joinPoint.getSignature().getName());
//        builderLog.append("，请求方式 : ").append(method);
//        builderLog.append("，入参 : ").append(args);
//        return builderLog.toString();
//    }
//
//    /**
//     * 获取请求入参
//     * @param request
//     * @param joinPoint
//     * @return
//     */
//    private String getArgs(HttpServletRequest request,ProceedingJoinPoint joinPoint){
//        String param = JSON.toJSONString(joinPoint.getArgs());
//        String method = request.getMethod().toUpperCase();
//        if(Objects.equals(METHOD_GET,method)) {
//            // 获取get请求的参数
//            param = "["+request.getQueryString()+"]";
//        }
//        return  param;
//    }
//
//    /**
//     * 构建使用@OperLog注解的操作日志信息
//     * @param request
//     * @param joinPoint
//     * @param exeTime 执行时间
//     */
//    private  void builderOperLog(HttpServletRequest request,ProceedingJoinPoint joinPoint,long exeTime,String args,String result)
//            throws NoSuchMethodException {
//        Class<?> target = joinPoint.getTarget().getClass();
//        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//        Class<?>[] types = signature.getParameterTypes();
//        Method m = target.getMethod(signature.getName(), types);
//        if (m.isAnnotationPresent(OperLog.class)) {
//            OperLog operLog = m.getAnnotation(OperLog.class);
//            LogDTO logDTO = new LogDTO();
//            logDTO.setTitle(operLog.desc());
//            logDTO.setServiceName((String)CsPropsUtil.getProperty("spring.application.name"));
//            logDTO.setHandleIp(CsWebUtil.getIpAddr());
//            logDTO.setReqMethod(request.getMethod().toUpperCase());
//            logDTO.setReqParams(args);
//            logDTO.setCreateBy(CsSecureUtil.userAccount());
//            logDTO.setResultMsg(result);
//            logDTO.setReqUrl(request.getRequestURI());
//            logDTO.setExeTime(exeTime);
//            // 发布操作日志事件，异步执行
//            CsSpringUtil.publishEvent(new LogEvent(this,logDTO));
//        }
//    }
//}
