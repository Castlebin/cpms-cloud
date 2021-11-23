package com.cpms.auth.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @description:
 * @author: gulang
 * @time: 2021/6/7 10:42
 */
@Data
public class UserLoginDTO {
    /**
     * 登录用户账号
     */
    @NotBlank(message="account不能为空")
    @NotNull(message="account不能为空")
    private String account;
    /**
     * 登录用户密码
     */
    @NotBlank(message="password不能为空")
    @NotNull(message="password不能为空")
    private String password;

    /**
     * 验证码
     */
    @NotBlank(message="captcha不能为空")
    @NotNull(message="captcha不能为空")
    private String captcha;

    /**
     * 验证码缓存key
     */
    @NotBlank(message="codeKey不能为空")
    @NotNull(message="codeKey不能为空")
    private String codeKey;

    /**
     * 授权类型
     */
    @NotBlank(message="grantType授权类型不能为空")
    private String grantType;
}
