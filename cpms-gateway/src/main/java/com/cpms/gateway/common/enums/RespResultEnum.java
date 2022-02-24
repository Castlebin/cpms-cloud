package com.cpms.gateway.common.enums;


/**
 * @description:
 * @author: gulang
 * @time: 2021/5/25 15:49
 */
public enum RespResultEnum {
    /**网关服务响应枚举**/
    INTERNAL_SERVER_BUSY_ERROR(40000, "系统繁忙，请稍后再试！！！"),
    LOSE_AUTH_TOKEN_ERROR(40001, "缺失token令牌,登录鉴权失败"),
    TOKEN_CHECK_INVALID_ERROR(40002, "token令牌校验不合法"),
    TOKEN_EXPIRED_ERROR(40003, "token令牌已过期"),
    SENTINEL_BLOCK_ERROR(40004, "已限流，请稍后再试"),
    ;
    final Integer code;
    final String message;



    RespResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


    public Integer getCode() {
        return code;
    }


    public String getMessage() {
        return message;
    }
}
