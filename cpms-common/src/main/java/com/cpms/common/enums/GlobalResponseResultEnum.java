package com.cpms.common.enums;

import com.cpms.common.core.api.IResultCode;

/**
 * @description: 全局响应枚举
 * @author: gulang
 * @time: 2021/5/24 19:09
 */
public enum GlobalResponseResultEnum implements IResultCode {
    /**全局响应枚举 */
    SUCCESS(20000, "响应成功"),
    UN_AUTHENTICATION_ERROR(20001, "未登录或登录失效"),
    METHOD_NOT_SUPPORTED_ERROR(20002, "不支持当前请求方法"),
    REQ_UNAUTHORIZED_ERROR(20003, "请求未授权"),
    PARAM_MISS_ERROR(20004, "缺少必要的请求参数"),
    PARAM_VALID_ERROR(20005, "参数校验失败"),
    QUERY_DATA_NOT_EXIST_ERROR(20006, "查询数据不存在"),
    INTERNAL_SERVER_ERROR(50000, "系统繁忙，请稍后再试！！！"),
    ;


    /**
     * 返回编码
     */
    final Integer code;
    /**
     * 返回信息
     */
    final String message;
    @Override
    public Integer getCode() { return this.code;}
    @Override
    public String getMessage() {return this.message;}
    GlobalResponseResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
