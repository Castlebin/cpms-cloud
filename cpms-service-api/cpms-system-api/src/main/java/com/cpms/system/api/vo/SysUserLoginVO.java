package com.cpms.system.api.vo;

import com.cpms.common.core.secure.UserLoginBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @description:
 * @author: gulang
 * @time: 2021/7/21 10:41
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysUserLoginVO extends UserLoginBase {
    /**
     * 权限标识集合
     */
    private List<String> permissions;

    /**
     * 角色集合
     */
    private List<String> roles;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 部门ID
     */
    private String deptName;

    /**
     * 租户ID
     */
    private Long tenantId;

    /**
     * 租户名称
     */
    private String tenantName;
}
