package com.cpms.gateway.fallback;

import com.cpms.common.core.api.Result;
import com.cpms.common.core.api.ResultUtil;
import com.cpms.common.enums.GlobalResponseResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;


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
    public Result<Void> defaultfallback(ServerWebExchange exchange){
        Exception exception = exchange.getAttribute(ServerWebExchangeUtils.HYSTRIX_EXECUTION_EXCEPTION_ATTR);
        log.error("[defaultfallback]触发网关层熔断降级默认回调方法", exception);
        return ResultUtil.error(GlobalResponseResultEnum.INTERNAL_SERVER_ERROR);
    }
}
