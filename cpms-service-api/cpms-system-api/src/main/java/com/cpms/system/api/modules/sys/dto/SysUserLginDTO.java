package com.cpms.system.api.modules.sys.dto;

import lombok.Data;

/**
 * @author gualng
 * @Description:
 */
@Data
public class SysUserLginDTO {
    /**
     * 登录用户账号
     */
    private String userAccount;
    /**
     * 登录用户密码
     */
    private String userPassword;
}
