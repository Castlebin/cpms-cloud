package com.cpms.auth.common.enums;


import com.cpms.framework.common.core.api.IResultEnum;

/**
 * @description:
 * @author: gulang
 * @time: 2021/5/25 15:49
 */
public enum AuthResponseResultEnum implements IResultEnum {
    /** 授权服务响应枚举类 */
     CAPTCHA_VERIFICATION_EEROR(10000, "验证码不正确"),
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

    AuthResponseResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
