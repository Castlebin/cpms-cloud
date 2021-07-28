package com.cpms.framework.security.config;

import com.cpms.framework.security.handler.RequestHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import javax.annotation.Resource;


/**
 * @description:
 * @author: gulang
 * @time: 2021/7/28 11:23
 */
@Configuration
public class RequestWebMvcConfig implements WebMvcConfigurer {
    @Resource
    private RequestHandlerInterceptor requestHandlerInterceptor;

    /**
     * 将登录拦截器配置到容器中
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //所有路径都被拦截
        registry.addInterceptor(requestHandlerInterceptor).addPathPatterns("/**");
    }

}