package com.cpms.common.enums;

import com.cpms.common.core.api.IResultEnum;

/**
 * @description: 全局响应枚举
 * @author: gulang
 * @time: 2021/5/24 19:09
 */
public enum GlobalResponseResultEnum implements IResultEnum {
    /**全局响应枚举 */
    SUCCESS(20000, "响应成功"),
    LOSE_AUTH_TOKEN_ERROR(20001, "缺失token令牌,登录鉴权失败"),
    METHOD_NOT_SUPPORTED_ERROR(20002, "不支持当前请求方式"),
    REQ_UNAUTHORIZED_ERROR(20003, "请求未授权"),
    PARAM_MISS_ERROR(20004, "缺少必要的请求参数"),
    PARAM_VALID_ERROR(20005, "参数校验失败"),
    QUERY_DATA_NOT_EXIST_ERROR(20006, "查询数据不存在"),
    NO_HANDLER_FOUND_ERROR(20007, "请求资源没有被发现"),
    HANDEL_SUCCESS(20008, "操作成功！！！"),
    HANDEL_FAIL(20009, "操作失败！！！"),
    TOKEN_CHECK_INVALID_ERROR(20010, "token令牌校验不合法"),
    TOKEN_EXPIRED_ERROR(20011, "token令牌已过期"),
    PARAMETER_BOYDY_EMPTY_ERROR(20012,"参数体不能为空"),
    INTERNAL_SERVER_BUSY_ERROR(50000, "系统繁忙，请稍后再试！！！"),
    INTERNAL_SERVER_EXCEPTION_ERROR(50001, "系统异常！！！"),
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
