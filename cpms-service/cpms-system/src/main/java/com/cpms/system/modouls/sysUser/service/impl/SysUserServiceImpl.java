package com.cpms.system.modouls.sysUser.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cpms.system.api.dto.SysUserLginDTO;
import com.cpms.system.modouls.sysUser.entity.SysUserEntity;
import com.cpms.system.modouls.sysUser.mapper.SysUserMapper;
import com.cpms.system.modouls.sysUser.service.ISysUserService;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: gulang
 * @time: 2021/7/16 19:36
 */
@Service
@DS("master")
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserEntity> implements ISysUserService {
    @Override
    public SysUserEntity queryUserInfo(SysUserLginDTO sysUserLginDTO) {
        return baseMapper.selectOne(
                Wrappers.<SysUserEntity>query().lambda().eq(SysUserEntity::getUserName, sysUserLginDTO.getUserName())
                .eq(SysUserEntity::getUserPassword, sysUserLginDTO.getPassword()).eq(SysUserEntity::getDelFlag,0));
    }

    @Override
    public boolean saveOrUpdateSysUser() {
        return false;
    }
}
