package com.cpms.system.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author gulang
 * @Description:
 * @time: 2021/11/10 21:19
 */
@Data
@TableName("cpms_system_role_menu")
public class SysRoleMenuEntity {
    private Long roleId;
    private Long menuId;
}
