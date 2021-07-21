package com.cpms.system.modouls.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cpms.system.api.bo.SysUserLoginBO;
import com.cpms.system.api.dto.SysUserLginDTO;
import com.cpms.system.modouls.sys.entity.SysUserEntity;

/**
 * @description:
 * @author: gulang
 * @time: 2021/7/16 19:27
 */
public interface SysUserMapper extends BaseMapper<SysUserEntity> {
    /**
     *  查询用户信息
     * @param sysUserLginDTO
     * @return
     */
    SysUserLoginBO querySysUserInfo(SysUserLginDTO sysUserLginDTO);
}
