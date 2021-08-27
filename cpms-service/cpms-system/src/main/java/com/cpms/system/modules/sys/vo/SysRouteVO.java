package com.cpms.system.modules.sys.vo;

import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: gulang
 * @time: 2021/8/23 16:57
 */
@Data
public class SysRouteVO {
    List<SysMenuVO> menus;
    List<String> buttons;
}
