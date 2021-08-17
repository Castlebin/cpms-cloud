package com.cpms.system.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cpms.system.modules.sys.entity.SysRoleEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description:
 * @author: gulang
 * @time: 2021/8/6 17:10
 */
public interface SysRoleMapper extends BaseMapper<SysRoleEntity> {
    List<SysRoleEntity> queryRoleByUserId(@Param("userId") Long userId);
}
