package com.cpms.system.modouls.sys.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cpms.system.modouls.sys.entity.SysRoleEntity;
import com.cpms.system.modouls.sys.mapper.SysRoleMapper;
import com.cpms.system.modouls.sys.service.ISysRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author: gulang
 * @time: 2021/8/6 17:51
 */
@Service
@DS("master")
public class SysRoleServiceImpl  extends ServiceImpl<SysRoleMapper, SysRoleEntity> implements ISysRoleService {
    @Resource
    private SysRoleMapper sysRoleMapper;

    @Override
    public List<SysRoleEntity> queryRoleByUserId(Long userId) {
        return sysRoleMapper.queryRoleByUserId(userId);
    }
}
