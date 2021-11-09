package com.cpms.system.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cpms.framework.mybatis.entity.TenantEntity;
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
    /**
     *  指定自增策略
     */
    @TableId(value = "role_id",type = IdType.ASSIGN_ID)
    private Long roleId;
    private String roleName;
    private String roleCode;
    private String roleDesc;
    private Integer roleSort;
}
