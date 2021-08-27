package com.cpms.framework.log.config;

import com.cpms.framework.log.filter.ChannelFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * @description: 自定义filter注册
 * @author: gulang
 * @time: 2021/8/23 15:15
 */
@Configuration
public class FilterRegistConfig {
    @Bean("channelFilter")
    @SuppressWarnings("all")
    public FilterRegistrationBean ChannelFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new ChannelFilter());
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
        return filterRegistrationBean;
    }
}
