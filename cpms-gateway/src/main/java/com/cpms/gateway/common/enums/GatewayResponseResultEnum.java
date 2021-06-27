package com.cpms.gateway.common.enums;

import com.cpms.common.core.api.IResultCode;

/**
 * @description:
 * @author: gulang
 * @time: 2021/5/25 15:49
 */
public enum GatewayResponseResultEnum implements IResultCode {
    /**网关服务响应枚举**/
    ACCOUNT_NOT_EXIST_ERROR(40000, "账号不存在"),
    PASSWORD_VERIFICATION_ERROR(40001, "密码校验错误"),

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
