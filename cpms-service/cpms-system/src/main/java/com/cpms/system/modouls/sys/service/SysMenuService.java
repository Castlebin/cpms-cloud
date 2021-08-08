package com.cpms.system.modouls.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cpms.system.modouls.sys.entity.SysMenuEntity;
import com.cpms.system.modouls.sys.vo.SysMenuVO;

import java.util.List;

/**
 * @author gulang
 * @Description:
 * @time: 2021/8/7 20:51
 */
public interface SysMenuService extends IService<SysMenuEntity> {
    List<SysMenuVO> querySysMenuRoutes(Long topMenuId);
}
