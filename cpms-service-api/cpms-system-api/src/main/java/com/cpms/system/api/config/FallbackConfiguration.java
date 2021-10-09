package com.cpms.system.api.config;

import com.cpms.system.api.modules.sys.feign.fallback.SysUserClientFallback;
import com.cpms.system.api.modules.sys.feign.factory.SysUserClientFallbackFactory;
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
    public SysUserClientFallback sysUserClientFallback() {
        return new SysUserClientFallback();
    }
    @Bean
    public SysUserClientFallbackFactory sysUserClientFallbackFactory(){
        return new SysUserClientFallbackFactory();
    }
}
