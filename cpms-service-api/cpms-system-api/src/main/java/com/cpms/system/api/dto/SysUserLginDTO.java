package com.cpms.system.api.dto;

import lombok.Data;

/**
 * @author gualng
 * @Description:
 */
@Data
public class SysUserLginDTO {
    /**
     * 登录用户名
     */
    private String userName;
    /**
     * 登录用户密码
     */
    private String password;
}
