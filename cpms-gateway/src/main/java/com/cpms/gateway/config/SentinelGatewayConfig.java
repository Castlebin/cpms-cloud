package com.cpms.gateway.config;

import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import com.cpms.gateway.common.constants.SystemConstant;
import com.cpms.gateway.common.enums.GatewayResponseResultEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: gulang
 * @time: 2021/12/30 16:19
 */
@Configuration
public class SentinelGatewayConfig {
    private static Logger logger = LoggerFactory.getLogger(SentinelGatewayConfig.class);
    @Autowired
    private ObjectMapper objectMapper;

    public SentinelGatewayConfig(){
        GatewayCallbackManager.setBlockHandler((serverWebExchange, throwable) -> {
            ServerHttpResponse serverHttpResponse = serverWebExchange.getResponse();
            serverHttpResponse.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
            String result = "";
            Map<String, Object> map = new HashMap<>(16);
            map.put("code", GatewayResponseResultEnum.SENTINEL_BLOCK_ERROR.getCode());
            map.put("message", GatewayResponseResultEnum.SENTINEL_BLOCK_ERROR.getMessage());
            map.put("obj", null);
            map.put("date", FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss").format(new Date()));
            map.put(SystemConstant.TRACE_ID, MDC.get(SystemConstant.TRACE_ID));
            try {
                result = objectMapper.writeValueAsString(map);
            } catch (JsonProcessingException e) {
                logger.error("触发sentinel限流回调，捕获异常", e);
            }
            DataBuffer buffer = serverHttpResponse.bufferFactory().wrap(result.getBytes(StandardCharsets.UTF_8));
            MDC.clear();
            return ServerResponse.ok().body(Flux.just(buffer),DataBuffer.class);
        });
    }
}
