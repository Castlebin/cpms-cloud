package com.cpms.gateway.filter;

import com.cpms.gateway.common.constants.SystemConstant;
import com.cpms.gateway.props.AuthUrlProperties;
import com.cpms.gateway.props.DefaultUrlProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @description: 鉴权过滤器
 * @author: gulang
 * @time: 2021/7/21 16:46
 */
@Slf4j
@Component
public class AuthFilter implements GlobalFilter, Ordered {
    @Autowired
    private AuthUrlProperties authUrlProperties;
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();
        if (isSkip(path)) {
            return chain.filter(exchange);
        }
        List<String> skipUrl = authUrlProperties.getSkipUrl();
        System.out.println(authUrlProperties.getSkipUrl());
        return chain.filter(exchange);
    }

    private boolean isSkip(String path) {
        return DefaultUrlProperties.getDefaultSkipUrl().stream().map(url -> url.replace(SystemConstant.TARGET, SystemConstant.REPLACEMENT)).anyMatch(path::contains)
                || authUrlProperties.getSkipUrl().stream().map(url -> url.replace(SystemConstant.TARGET, SystemConstant.REPLACEMENT)).anyMatch(path::contains);
    }

    @Override
    public int getOrder() {
        // 值越小越先执行
        return -100;
    }
}
