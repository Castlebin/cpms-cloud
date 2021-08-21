package com.cpms.system.modules.sys.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cpms.system.api.modules.sys.bo.SysUserLoginBO;
import com.cpms.system.api.modules.sys.dto.SysUserLginDTO;
import com.cpms.system.modules.sys.entity.SysUserEntity;
import com.cpms.system.modules.sys.mapper.SysUserMapper;
import com.cpms.system.modules.sys.service.ISysUserService;
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

}
