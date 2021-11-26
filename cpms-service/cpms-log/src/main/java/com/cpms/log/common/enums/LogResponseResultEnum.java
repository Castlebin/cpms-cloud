package com.cpms.log.common.enums;


import com.cpms.framework.common.core.api.IResultEnum;

/**
 * @description:
 * @author: 01396003
 * @time: 2021/5/25 15:49
 */
public enum LogResponseResultEnum implements IResultEnum {
    /**日志服务响应枚举**/
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
