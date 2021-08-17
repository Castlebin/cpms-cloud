package com.cpms.system.modules.sys.entity;

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
    /**
     *  指定自增策略
     */
    @TableId(value = "user_id",type = IdType.ASSIGN_ID)
    private Long userId;
    private Long tenantId;
    private Long deptId;
    private String userName;
    private String userPassword;
    private String userLoginIp;
    private LocalDateTime lastLoginTime;
    /**
     * 用户账号
     */
    private String userAccount;
    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 用户手机号
     */
    private String userMobile;
    /**
     * 用户性别：0-未知，1-男，2-女
     */
    private Integer  userSex;
}
