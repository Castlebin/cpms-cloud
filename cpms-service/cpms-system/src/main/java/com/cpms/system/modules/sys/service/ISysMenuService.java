package com.cpms.system.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cpms.system.modules.sys.dto.SysMenuDTO;
import com.cpms.system.modules.sys.entity.SysMenuEntity;
import com.cpms.system.modules.sys.vo.SysRouteVO;


/**
 * @author gulang
 * @Description:
 * @time: 2021/8/7 20:51
 */
public interface ISysMenuService extends IService<SysMenuEntity> {
    SysRouteVO querySysMenuRoutes(Long topMenuId);
    boolean addMenu(SysMenuDTO sysMenuDTO);
    boolean updateMenu(SysMenuDTO sysMenuDTO);
    boolean deleteMenu(SysMenuDTO sysMenuDTO);
}
