package com.cpms.system.modules.sys.dto;

import com.cpms.framework.mybatis.groups.AddGroup;
import com.cpms.framework.mybatis.groups.DeleteGroup;
import com.cpms.framework.mybatis.groups.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
 * @author gulang
 * @Description:
 * @time: 2021/8/7 20:47
 */
@Data
public class SysMenuDTO {
    @NotNull(message="menuId不能为空",groups = {UpdateGroup.class, DeleteGroup.class})
    private Long menuId;
    private Long parentId;

    @NotBlank(message="name不能为空",groups = {UpdateGroup.class, AddGroup.class})
    @NotNull(message="name不能为空",groups = {UpdateGroup.class, AddGroup.class})
    private String name;
    @NotBlank(message="code不能为空",groups = {UpdateGroup.class, AddGroup.class})
    @NotNull(message="code不能为空",groups = {UpdateGroup.class, AddGroup.class})
    private String code;
    private String alias;
    private String path;
    private String icon;
    private String component;
    private Integer sort;
    private Integer type;
    private Integer openFlag;

}
