package com.cpms.auth.common.enums;

import com.cpms.common.core.api.IResultEnum;

/**
 * @description:
 * @author: gulang
 * @time: 2021/5/25 15:49
 */
public enum AuthResponseResultEnum implements IResultEnum {
    /** 账号不存在 */
    ACCOUNT_NOT_EXIST_ERROR(10000, "账号不存在"),
    PASSWORD_VERIFICATION_ERROR(10001, "密码错误"),
    ;

    final Integer code;
    final String message;

    @Override
    public Integer getCode() {
        return null;
    }

    @Override
    public String getMessage() {
        return null;
    }

    AuthResponseResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
