package com.cpms.system.modouls.sysUser.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cpms.common.core.base.TenantEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @description: 系统管理用户表实体类
 * @author: gulang
 * @time: 2021/7/16 19:13
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("cpms_system_user")
public class SysUserEntity extends TenantEntity {
    private static final long serialVersionUID = 1L;
    @TableId(value = "user_id",type = IdType.ASSIGN_ID)//指定自增策略
    private Long userId;
    private Long tenantId;
    private Long deptId;
    private String userName;
    private String userPassword;
    private String userLoginIp;
    private LocalDateTime userLoginTime;
}
