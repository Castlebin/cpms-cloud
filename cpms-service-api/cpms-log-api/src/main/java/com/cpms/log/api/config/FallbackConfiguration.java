package com.cpms.log.api.config;

import com.cpms.log.api.modules.log.feign.fallback.LogClientFallback;
import com.cpms.log.api.modules.log.feign.factory.LogClientFallbackFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @author gulang
 * @Description:
 */
@Configuration
@Order(-2147483638)
public class FallbackConfiguration {
    public FallbackConfiguration() {
    }
    @Bean
    public LogClientFallback logClientFallback() {
        return new LogClientFallback();
    }
    @Bean
    public LogClientFallbackFactory logClientFallbackFactory(){
        return new LogClientFallbackFactory();
    }
}
