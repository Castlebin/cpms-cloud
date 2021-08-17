package com.cpms.system.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cpms.system.api.bo.SysUserLoginBO;
import com.cpms.system.api.dto.SysUserLginDTO;
import com.cpms.system.modules.sys.entity.SysUserEntity;

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
    SysUserLoginBO querySysUserInfo(SysUserLginDTO sysUserLginDTO);

}
