package com.cpms.system.common.enums;

import com.cpms.common.core.api.IResultCode;

/**
 * @description:
 * @author: gulang
 * @time: 2021/5/25 15:49
 */
public enum SystemResponseResultEnum implements IResultCode {
    /**系统服务响应枚举类**/
    ACCOUNT_NOT_EXIST_ERROR(50001, "账号不存在"),
    PASSWORD_VERIFICATION_ERROR(50002, "密码校验错误"),
    ;

    final Integer code;
    final String message;

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    SystemResponseResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
