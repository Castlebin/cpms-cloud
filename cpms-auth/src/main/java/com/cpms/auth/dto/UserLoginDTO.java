package com.cpms.auth.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

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
    private String account;
    /**
     * 登录用户密码
     */
    private String password;

    /**
     * 授权类型
     */
    @NotBlank(message="grantType授权类型不能为空")
    private String grantType;
}
