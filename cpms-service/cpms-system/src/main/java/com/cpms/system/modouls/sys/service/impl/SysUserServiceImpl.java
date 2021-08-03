package com.cpms.system.modouls.sys.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cpms.system.api.bo.SysUserLoginBO;
import com.cpms.system.api.dto.SysUserLginDTO;
import com.cpms.system.modouls.sys.entity.SysUserEntity;
import com.cpms.system.modouls.sys.mapper.SysUserMapper;
import com.cpms.system.modouls.sys.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description:
 * @author: gulang
 * @time: 2021/7/16 19:36
 */
@Service
@DS("master")
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserEntity> implements ISysUserService {
    @Resource
    private  SysUserMapper sysUserMapper;
    @Override
    public SysUserLoginBO querySysUserInfo(SysUserLginDTO sysUserLginDTO) {
        return sysUserMapper.querySysUserInfo(sysUserLginDTO);
    }

    @Override
    public boolean saveOrUpdateSysUser() {
        return false;
    }
}
