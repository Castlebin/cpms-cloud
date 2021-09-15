package com.cpms.system.modules.sys.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cpms.system.modules.sys.entity.SysRoleUserEntity;

/**
 * @description:
 * @author: gulang
 * @time: 2021/9/15 11:40
 */
@DS("master")
public interface SysRoleUserMapper extends BaseMapper<SysRoleUserEntity> {
}
