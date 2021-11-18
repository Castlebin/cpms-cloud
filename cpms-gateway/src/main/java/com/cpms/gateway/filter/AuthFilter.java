package com.cpms.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.cpms.framework.common.constants.CoreCommonConstant;
import com.cpms.framework.common.core.api.ResultUtil;
import com.cpms.framework.common.enums.GlobalResponseResultEnum;
import com.cpms.framework.common.exception.BizException;
import com.cpms.framework.common.exception.CheckJwtException;
import com.cpms.framework.common.utils.CsJwtUtil;
import com.cpms.gateway.common.constants.SystemConstant;
import com.cpms.gateway.props.AuthUrlProperties;
import com.cpms.gateway.props.DefaultUrlProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

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
    @Autowired
    private ObjectMapper objectMapper;

    @SneakyThrows
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();
        if (isSkip(path)) {
            return chain.filter(exchange);
        }
        ServerHttpResponse resp = exchange.getResponse();
        String headerToken = exchange.getRequest().getHeaders().getFirst(CoreCommonConstant.H_TOKEN_KEY);
        String paramToken = exchange.getRequest().getQueryParams().getFirst(CoreCommonConstant.H_TOKEN_KEY);
        if (StringUtils.isAllBlank(headerToken, paramToken)) {
            return unAuth(resp, GlobalResponseResultEnum.LOSE_AUTH_TOKEN_ERROR.getCode(),GlobalResponseResultEnum.LOSE_AUTH_TOKEN_ERROR.getMessage());
        }
        String token = StringUtils.isBlank(headerToken) ? paramToken : headerToken;
        Claims claims;
        try{
            claims = CsJwtUtil.parseJwt(token);
        }catch (CheckJwtException e){
            return unAuth(resp, e.getCode(),e.getMessage());
        }
        claims.put("tokenExpire",claims.get("exp"));
        // 设置用户信息到请求头，传递到下游微服务
        ServerHttpRequest mutableReq = exchange.getRequest().mutate().header(CoreCommonConstant.USER_INFO, URLEncoder.encode(JSON.toJSONString(claims), "UTF-8")).build();
        ServerWebExchange mutableExchange = exchange.mutate().request(mutableReq).build();
        return chain.filter(mutableExchange);
    }

    private boolean isSkip(String path) {
        return DefaultUrlProperties.getDefaultSkipUrl().stream().map(url -> url.replace(SystemConstant.TARGET, SystemConstant.REPLACEMENT)).anyMatch(path::contains)
                || authUrlProperties.getSkipUrl().stream().map(url -> url.replace(SystemConstant.TARGET, SystemConstant.REPLACEMENT)).anyMatch(path::contains);
    }

    private Mono<Void> unAuth(ServerHttpResponse resp, Integer code,String message) {
        resp.setStatusCode(HttpStatus.UNAUTHORIZED);
        resp.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        String result = "";
        try {
            result = objectMapper.writeValueAsString(ResultUtil.error(code,message,null));
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
        DataBuffer buffer = resp.bufferFactory().wrap(result.getBytes(StandardCharsets.UTF_8));
        return resp.writeWith(Flux.just(buffer));
    }

    @Override
    public int getOrder() {
        // 值越小越先执行
        return -100;
    }

}
