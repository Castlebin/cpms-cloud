package com.cpms.common.config;

import com.cpms.common.utils.SpringUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @description: 工具类对象注入
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
}
