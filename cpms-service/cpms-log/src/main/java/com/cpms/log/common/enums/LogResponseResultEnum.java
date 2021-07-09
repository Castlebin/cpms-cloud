package com.cpms.log.common.enums;

import com.cpms.common.core.api.IResultEnum;

/**
 * @description:
 * @author: 01396003
 * @time: 2021/5/25 15:49
 */
public enum LogResponseResultEnum implements IResultEnum {
    /**日志服务响应枚举**/
    ACCOUNT_NOT_EXIST_ERROR(30000, "账号不存在"),
    PASSWORD_VERIFICATION_ERROR(30001, "密码校验错误"),


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

    LogResponseResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
