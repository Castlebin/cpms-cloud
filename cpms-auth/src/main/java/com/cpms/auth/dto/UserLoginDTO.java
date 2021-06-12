package com.cpms.auth.dto;

import lombok.Data;

/**
 * @description:
 * @author: 01396003
 * @time: 2021/6/7 10:42
 */
@Data
public class UserLoginDTO {
    /**
     * 登录用户名
     */
    private String userName;
    /**
     * 登录用户密码
     */
    private String password;

    /**
     * 登录类型
     */
    private String loginType;
}
