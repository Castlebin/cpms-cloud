package com.cpms.system.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cpms.system.modules.sys.entity.SysRoleUserEntity;

import java.util.List;

/**
 * @description:
 * @author: gulang
 * @time: 2021/9/15 11:40
 */
public interface SysRoleUserMapper extends BaseMapper<SysRoleUserEntity> {
    List<String> queryRoleCodeByUserId(Long userId);
}
