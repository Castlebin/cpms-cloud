package com.cpms.system.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cpms.system.modules.sys.entity.SysRoleEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description:
 * @author: gulang
 * @time: 2021/8/6 17:50
 */
public interface ISysRoleService extends IService<SysRoleEntity> {
    List<SysRoleEntity> queryRoleByUserId(@Param("userId") Long userId);
}
