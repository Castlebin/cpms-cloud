package com.cpms.lowcode.common.enums;


import com.cpms.framework.common.core.api.IResultEnum;

/**
 * @description:
 * @author: gulang
 * @time: 2021/5/25 15:49
 */
public enum RespResultEnum implements IResultEnum {
    /**日志服务响应枚举**/
    CREAT_DATABASE_CONNECT_FAIL_ERROR(60001, "创建数据库连接失败！！！"),
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

    RespResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
