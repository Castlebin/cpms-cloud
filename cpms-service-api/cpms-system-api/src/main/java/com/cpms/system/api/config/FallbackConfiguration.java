package com.cpms.system.api.config;

import com.cpms.system.api.feign.ISysUserClientFallback;
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
    public ISysUserClientFallback iSysUserClientFallback() {
        return new ISysUserClientFallback();
    }
}
