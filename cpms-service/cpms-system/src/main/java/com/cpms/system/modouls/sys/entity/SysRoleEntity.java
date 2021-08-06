package com.cpms.system.modouls.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cpms.common.core.base.TenantEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description:
 * @author: gulang
 * @time: 2021/8/6 17:02
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("cpms_system_role")
public class SysRoleEntity extends TenantEntity {
    private Long roleId;
    private String roleName;
    private String roleCode;
    private String roleDesc;
}
