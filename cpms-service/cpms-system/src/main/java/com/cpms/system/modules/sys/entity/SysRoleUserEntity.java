package com.cpms.system.modules.sys.entity;

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
    private Long roleId;
    private Long userId;
}
