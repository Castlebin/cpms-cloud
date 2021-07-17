package com.cpms.system.modouls.sysUser.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cpms.system.api.dto.SysUserLginDTO;
import com.cpms.system.modouls.sysUser.entity.SysUserEntity;

/**
 * @description:
 * @author: gulang
 * @time: 2021/7/16 19:30
 */
public interface ISysUserService extends IService<SysUserEntity> {
    /**
     *  登录用户信息查询
     * @param sysUserLginDTO
     * @return
     */
    SysUserEntity queryUserInfo(SysUserLginDTO sysUserLginDTO);

    /**
     *  新增或修改用户
     * @return
     */
    boolean  saveOrUpdateSysUser();
}
