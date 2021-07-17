package com.cpms.system.common.enums;

import com.cpms.common.core.api.IResultEnum;

/**
 * @description:
 * @author: gulang
 * @time: 2021/5/25 15:49
 */
public enum SystemResponseResultEnum implements IResultEnum {
    /**系统服务响应枚举类**/
    ACCOUNT_OR_PASSWORD_CHECK_FAILED_ERROR(50002, "账号或密码不正确！！！"),
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
