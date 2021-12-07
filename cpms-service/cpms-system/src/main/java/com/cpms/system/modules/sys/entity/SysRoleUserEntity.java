package com.cpms.system.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @description:
 * @author: gulang
 * @time: 2021/9/15 11:37
 */
@Data
@TableName("cpms_system_role_user")
public class SysRoleUserEntity {
    @TableId(value = "role_user_id",type = IdType.ASSIGN_ID)
    private Long roleUserId;
    private Long roleId;
    private Long userId;
}
