package com.cpms.system.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cpms.framework.common.core.base.BasePageVO;
import com.cpms.system.modules.sys.dto.QueryMenuDTO;
import com.cpms.system.modules.sys.dto.SysMenuDTO;
import com.cpms.system.modules.sys.entity.SysMenuEntity;
import com.cpms.system.modules.sys.vo.SysMenuVO;
import com.cpms.system.modules.sys.vo.SysRouteVO;

import java.util.List;


/**
 * @author gulang
 * @Description:
 * @time: 2021/8/7 20:51
 */
public interface ISysMenuService extends IService<SysMenuEntity> {
    SysRouteVO leftMenu(Long topMenuId);

    boolean addMenu(SysMenuDTO sysMenuDTO);

    boolean updateMenu(SysMenuDTO sysMenuDTO);

    boolean deleteMenu(SysMenuDTO sysMenuDTO);

    BasePageVO<SysMenuVO> listMenu(QueryMenuDTO listMenuDTO);

    List<SysMenuVO> userOwnedMenus();
    List<SysMenuVO> tenantOwnedMenus(Long tenantId);
}
