package com.cpms.system.api.config;

import com.cpms.system.api.feign.fallback.ISysUserClientFallback;
import com.cpms.system.api.feign.fallback.ISysUserClientFallbackFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @author gulang
 * @Description:
 */
@Configuration
@Order(-2147483648)
public class FallbackConfiguration {
    public FallbackConfiguration() {
    }
    @Bean
    public ISysUserClientFallback sysUserClientFallback() {
        return new ISysUserClientFallback();
    }
    @Bean
    public ISysUserClientFallbackFactory sysUserClientFallbackFactory(){
        return new ISysUserClientFallbackFactory();
    }
}
