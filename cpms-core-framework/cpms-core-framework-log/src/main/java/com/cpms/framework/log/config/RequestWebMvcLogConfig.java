package com.cpms.framework.log.config;

import com.cpms.framework.log.handler.RequestHandlerLogInterceptor;
import com.cpms.framework.log.handler.ResponseIntercept;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * @description: webMvc 日志拦截器配置
 * @author: gulang
 * @time: 2021/7/28 11:23
 */
@Configuration
public class RequestWebMvcLogConfig implements WebMvcConfigurer {
    @Bean
    public RequestHandlerLogInterceptor requestHandlerInterceptor() {
        return new RequestHandlerLogInterceptor();
    }

    @Bean
    public ResponseIntercept responseIntercept(){
        return  new ResponseIntercept();
    }

    /**
     * 将登录拦截器配置到容器中
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //所有路径都被拦截
        registry.addInterceptor(requestHandlerInterceptor()).addPathPatterns("/**");
    }

}