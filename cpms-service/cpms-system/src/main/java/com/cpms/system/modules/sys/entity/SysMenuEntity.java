package com.cpms.system.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cpms.common.core.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author gulang
 * @Description:
 * @time: 2021/8/7 20:40
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("cpms_system_menu")
public class SysMenuEntity extends BaseEntity {
    /**
     *  指定自增策略
     */
    @TableId(value = "menu_id",type = IdType.ASSIGN_ID)
    private Long menuId;
    private Long parentId;
    private String name;
    private String code;
    private String alias;
    private String path;
    private String icon;
    private String component;
    private Integer sort;
    private Integer type;
    private Integer openFlag;

}
