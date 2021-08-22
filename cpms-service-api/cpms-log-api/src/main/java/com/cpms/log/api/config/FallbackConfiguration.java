package com.cpms.log.api.config;

import com.cpms.log.api.modules.log.feign.fallback.ILogClientFallback;
import com.cpms.log.api.modules.log.feign.fallback.ILogClientFallbackFactory;
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
    public ILogClientFallback logClientFallback() {
        return new ILogClientFallback();
    }
    @Bean
    public ILogClientFallbackFactory logClientFallbackFactory(){
        return new ILogClientFallbackFactory();
    }
}
