package com.cpms.common.config;

import com.cpms.common.exception.GlobalExceptionHandler;
import com.cpms.common.utils.SpringUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @description: 在公共模块，使用了spring注解的类，由于扫描不到，不能被Spring管理，所以需要单独配置，
 * @author: gulang
 * @time: 2021/6/11 16:19
 */
@Configuration
@Order(-2147483648)
public class ToolConfiguration implements WebMvcConfigurer {
    public ToolConfiguration() {
    }

    @Bean
    public SpringUtil springUtil() {
        return new SpringUtil();
    }

    @Bean
    public GlobalExceptionHandler globalExceptionHandler() {
        return new GlobalExceptionHandler();
    }
}
