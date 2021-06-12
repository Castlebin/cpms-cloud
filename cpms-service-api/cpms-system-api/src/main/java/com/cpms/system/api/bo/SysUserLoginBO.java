package com.cpms.system.api.bo;

import com.cpms.common.core.secure.UserLoginBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @description: 系统用户登录BO类
 * @author: 01396003
 * @time: 2021/6/8 11:04
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysUserLoginBO extends UserLoginBase {
    private static final long serialVersionUID = 1L;

    /**
     * 权限标识集合
     */
    private List<String> permissions;

    /**
     * 角色集合
     */
    private List<String> roles;
}
