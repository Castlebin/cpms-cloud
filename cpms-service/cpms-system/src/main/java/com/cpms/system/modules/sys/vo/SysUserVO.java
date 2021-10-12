package com.cpms.system.modules.sys.vo;

import com.cpms.framework.common.core.base.BaseVO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @description:
 * @author: gulang
 * @time: 2021/9/15 16:40
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysUserVO extends BaseVO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;
    private String deptName;
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
    /**
     * 0-正常，1-已禁用
     */
    private Integer userStatus;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long postId;
    private String postName;
}
