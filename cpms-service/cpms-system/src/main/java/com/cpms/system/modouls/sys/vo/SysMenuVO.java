package com.cpms.system.modouls.sys.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gulang
 * @Description:
 * @time: 2021/8/7 20:47
 */
@Data
public class SysMenuVO {
    private Long menuId;
    private Long parentId;
    private List<SysMenuVO> children;
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
