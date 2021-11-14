package com.cpms.system.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cpms.system.modules.sys.entity.SysRoleUserEntity;
import com.cpms.system.modules.sys.mapper.SysRoleUserMapper;
import com.cpms.system.modules.sys.service.ISysRoleUserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: gulang
 * @time: 2021/9/15 11:51
 */
@Service
public class SysRoleUserServiceImpl extends ServiceImpl<SysRoleUserMapper, SysRoleUserEntity> implements ISysRoleUserService {

    @Override
    public List<String> queryRoleCodeByUserId(Long userId) {
        return baseMapper.queryRoleCodeByUserId(userId);
    }
}
