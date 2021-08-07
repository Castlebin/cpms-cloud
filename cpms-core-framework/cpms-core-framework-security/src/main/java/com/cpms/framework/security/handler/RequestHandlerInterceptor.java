//package com.cpms.framework.security.handler;
//
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * @description: 请求拦截器
// * @author: gulang
// * @time: 2021/7/27 19:21
// */
//@Component
//public class RequestHandlerInterceptor implements HandlerInterceptor {
//    /**
//     * 在请求处理之前进行调用（Controller方法调用之前）
//     * @return  1.如果设置为true时，请求将会继续执行后面的操作 2.如果设置为false时，被请求时，拦截器执行到此处将不会继续操作
//     */
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
//        System.out.println("-------请求拦截器-------");
//        return true;
//    }
//}
