package com.cpms.system.modules.sys.vo;

import com.cpms.framework.mybatis.entity.TenantEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @description:
 * @author: gulang
 * @time: 2021/9/15 16:40
 */
@Data
public class SysUserVO extends TenantEntity {
    private Long userId;
    private Long deptId;
    private String userName;
    private String userLoginIp;

    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss" ,timezone="GMT+8")
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
