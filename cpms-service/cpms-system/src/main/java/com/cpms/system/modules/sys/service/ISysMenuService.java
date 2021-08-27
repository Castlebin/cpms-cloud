package com.cpms.system.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
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
    SysRouteVO querySysMenuRoutes(Long topMenuId);
}
