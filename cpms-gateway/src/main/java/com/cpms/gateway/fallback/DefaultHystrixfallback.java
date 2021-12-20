package com.cpms.gateway.fallback;

import com.cpms.gateway.common.constants.SystemConstant;
import com.cpms.gateway.common.enums.GatewayResponseResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;

import java.util.HashMap;
import java.util.Map;


/**
 * @author gulang
 * @Description:  自定义网关层熔断降级默认回调方法
 * @time: 2021/7/2 20:35
 */
@RestController
@Slf4j
public class DefaultHystrixfallback {
    // 熔断回调一定要写成 @RequestMapping 这个注解，否则这个熔断方法不会触发
    @RequestMapping("/defaultfallback")
    public Map<String,Object> defaultfallback(ServerWebExchange exchange){
        String traceId = exchange.getRequest().getHeaders().getFirst(SystemConstant.TRACE_ID);
        MDC.put(SystemConstant.TRACE_ID, traceId);
        Exception exception = exchange.getAttribute(ServerWebExchangeUtils.HYSTRIX_EXECUTION_EXCEPTION_ATTR);
        log.error("[defaultfallback]触发网关层熔断降级默认回调方法,url={}",exchange.getRequest().getHeaders().getFirst(SystemConstant.GATEWAY_REQUEST_URL), exception);
        Map<String, Object> map = new HashMap<>(16);
        map.put("code", GatewayResponseResultEnum.INTERNAL_SERVER_BUSY_ERROR.getCode());
        map.put("msg", GatewayResponseResultEnum.INTERNAL_SERVER_BUSY_ERROR.getMessage());
        map.put("data", null);
        map.put(SystemConstant.TRACE_ID, traceId);
        MDC.clear();
        return map;
    }
}
