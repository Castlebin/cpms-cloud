package com.cpms.system.api.modules.sys.vo;

import com.cpms.framework.common.core.secure.UserLoginBase;
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
     * 角色集合
     */
    private List<SysRoleVO> roles;

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
