package com.cpms.system.modules.sys.vo;

import lombok.Data;

/**
 * @description:
 * @author: gulang
 * @time: 2021/8/30 14:29
 */
@Data
public class SysTopMenuVO {
    private Long topMenuId;
    private String topMenuName;
    private Integer sort;
    private String path;
}
