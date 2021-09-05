package com.cpms.gateway.common.enums;


import com.cpms.framework.common.core.api.IResultEnum;

/**
 * @description:
 * @author: gulang
 * @time: 2021/5/25 15:49
 */
public enum GatewayResponseResultEnum implements IResultEnum {
    /**网关服务响应枚举**/
    GATEWAY_SERVER_ERROR(40000, "网关服务异常"),

    ;
    final Integer code;
    final String message;



    GatewayResponseResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
