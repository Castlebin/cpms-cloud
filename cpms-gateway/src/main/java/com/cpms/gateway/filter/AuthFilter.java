package com.cpms.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.cpms.gateway.common.constants.SystemConstant;
import com.cpms.gateway.common.enums.GatewayResponseResultEnum;
import com.cpms.gateway.exception.CheckJwtException;
import com.cpms.gateway.props.AuthUrlProperties;
import com.cpms.gateway.props.CpmsProperties;
import com.cpms.gateway.props.DefaultUrlProperties;
import com.cpms.gateway.utils.IpUtil;
import com.cpms.gateway.utils.JwtUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.slf4j.MDC;
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

import javax.annotation.Resource;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
    @Resource
    private CpmsProperties cpmsProperties;

    @SneakyThrows
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();
        String traceId = getTraceId();
        // 不需要登录校验的直接跳过
        if (isSkip(path)) {
            // 设置用户信息到请求头，传递到下游微服务
            ServerHttpRequest mutableReq = exchange.getRequest().mutate()
                    .header(SystemConstant.CLIENT_IP_ADDR,IpUtil.getClientIp(exchange.getRequest()))
                    .header(SystemConstant.TRACE_ID,traceId)
                    .header(SystemConstant.GATEWAY_REQUEST_URL,path)
                    .build();
            ServerWebExchange mutableExchange = exchange.mutate().request(mutableReq).build();
            return chain.filter(mutableExchange);
        }
        ServerHttpResponse resp = exchange.getResponse();
        String headerToken = exchange.getRequest().getHeaders().getFirst(SystemConstant.H_TOKEN_KEY);
        String paramToken = exchange.getRequest().getQueryParams().getFirst(SystemConstant.H_TOKEN_KEY);
        if (StringUtils.isAllBlank(headerToken, paramToken)) {
            return unAuth(resp, GatewayResponseResultEnum.LOSE_AUTH_TOKEN_ERROR.getCode(),GatewayResponseResultEnum.LOSE_AUTH_TOKEN_ERROR.getMessage());
        }
        String token = StringUtils.isBlank(headerToken) ? paramToken : headerToken;
        Claims claims;
        try{
            claims = JwtUtil.parseJwt(token,cpmsProperties.getJwtSecretKey());
        }catch (CheckJwtException e){
            return unAuth(resp, e.getCode(),e.getMessage());
        }
        claims.put(SystemConstant.TOKEN_EXPIRE,claims.get("exp"));
        // 设置用户信息到请求头，传递到下游微服务
        ServerHttpRequest mutableReq = exchange.getRequest().mutate()
                .header(SystemConstant.USER_INFO, URLEncoder.encode(JSON.toJSONString(claims), "UTF-8"))
                .header(SystemConstant.CLIENT_IP_ADDR,IpUtil.getClientIp(exchange.getRequest()))
                .header(SystemConstant.TRACE_ID,traceId)
                .header(SystemConstant.GATEWAY_REQUEST_URL,path)
                .build();
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
            Map<String, Object> map = new HashMap<>(16);
            map.put("code", code);
            map.put("message", message);
            map.put("obj", null);
            map.put("date", FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss").format(new Date()));
            map.put(SystemConstant.TRACE_ID, MDC.get(SystemConstant.TRACE_ID));
            result = objectMapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
        DataBuffer buffer = resp.bufferFactory().wrap(result.getBytes(StandardCharsets.UTF_8));
        MDC.clear();
        return resp.writeWith(Flux.just(buffer));
    }

    @Override
    public int getOrder() {
        // 值越小越先执行
        return -100;
    }

    /**
     * 获取traceId传递到下游服务
     */
    private String getTraceId() {
        String traceId = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        MDC.put(SystemConstant.TRACE_ID, traceId);
        return traceId;
    }
}
