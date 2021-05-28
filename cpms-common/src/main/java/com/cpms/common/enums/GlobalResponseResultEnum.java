package com.cpms.common.enums;

import com.cpms.common.core.api.IResultCode;

/**
 * @description: 全局响应枚举
 * @author: 01396003
 * @time: 2021/5/24 19:09
 */
public enum GlobalResponseResultEnum implements IResultCode {
    SUCCESS(20000, "响应成功"),
    UN_AUTHENTICATION_ERROR(20001, "未登录或登录失效"),
    METHOD_NOT_SUPPORTED_ERROR(20002, "不支持当前请求方法"),
    REQ_UNAUTHORIZED_ERROR(20003, "请求未授权"),
    PARAM_MISS_ERROR(20004, "缺少必要的请求参数"),
    PARAM_VALID_ERROR(20005, "参数校验失败"),
    INTERNAL_SERVER_ERROR(50000, "服务器异常");


    /**
     * 返回编码
     */
    final int code;
    /**
     * 返回信息
     */
    final String message;
    public Integer getCode() { return this.code;}
    public String getMessage() {return this.message;}
    GlobalResponseResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
