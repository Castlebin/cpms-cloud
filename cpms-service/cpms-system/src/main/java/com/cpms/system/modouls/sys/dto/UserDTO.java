package com.cpms.system.modouls.sys.dto;

import lombok.Data;

/**
 * @description:
 * @author: gulang
 * @time: 2021/7/21 14:43
 */
@Data
public class UserDTO {
    /**
     * 用户userId
     */
    private Long userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户账号
     */
    private String userAccount;

    /**
     * 用户手机号
     */
    private String userMobile;
    /**
     * 用户性别：0-未知，1-男，2-女
     */
    private int  userSex;
}
