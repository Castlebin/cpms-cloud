package com.cpms.gateway.exception;

import com.alibaba.fastjson.JSON;
import com.cpms.gateway.common.enums.GatewayResponseResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.FastDateFormat;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.web.reactive.function.server.*;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author gulang
 * @Description:
 * @time: 2021/7/3 12:40
 */
@Slf4j
public class JsonErrorWebExceptionHandler extends DefaultErrorWebExceptionHandler {
    public JsonErrorWebExceptionHandler(ErrorAttributes errorAttributes,
                                        ResourceProperties resourceProperties,
                                        ErrorProperties errorProperties,
                                        ApplicationContext applicationContext) {
        super(errorAttributes, resourceProperties, errorProperties, applicationContext);
    }

    @Override
    protected Map<String, Object> getErrorAttributes(ServerRequest request, boolean includeStackTrace) {
        // 这里其实可以根据Throwable异常类型进行定制化逻辑
        Throwable error = super.getError(request);
        // 为了不破坏网关原有的http status状态码，所以这里继承原来的错误属性方法，过滤出需要显示的字段即可
        Map<String, Object> errorAttributes = super.getErrorAttributes(request, includeStackTrace);
        log.error("[getErrorAttributes]全局捕获网关服务异常，异常信息={}，error", JSON.toJSONString(errorAttributes),error);
        // 自定义错误属性返回字段
        Map<String, Object> customErrorAttributes = new LinkedHashMap();
        customErrorAttributes.put("success",false);
        customErrorAttributes.put("code", errorAttributes.get("status"));
        customErrorAttributes.put("message",GatewayResponseResultEnum.GATEWAY_SERVER__ERROR.getMessage());
        customErrorAttributes.put("date", FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss").format(new Date()));
        customErrorAttributes.put("obj",null);
        return customErrorAttributes;
    }

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
    }

    @Override
    protected int getHttpStatus(Map<String, Object> errorAttributes) {
        // 这里其实可以根据errorAttributes里面的属性定制HTTP响应码,
        return (int)errorAttributes.get("code");
    }
}
