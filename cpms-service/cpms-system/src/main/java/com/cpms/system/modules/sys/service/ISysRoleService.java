package com.cpms.system.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cpms.framework.common.core.base.BasePageVO;
import com.cpms.system.modules.sys.dto.QueryRoleDTO;
import com.cpms.system.modules.sys.dto.SysRoleDTO;
import com.cpms.system.modules.sys.entity.SysRoleEntity;
import com.cpms.system.modules.sys.vo.SysRoleVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description:
 * @author: gulang
 * @time: 2021/8/6 17:50
 */
public interface ISysRoleService extends IService<SysRoleEntity> {
    List<SysRoleEntity> queryRoleByUserId(@Param("userId") Long userId);
    BasePageVO<SysRoleVO> listRole(QueryRoleDTO listRoleDTO);
    boolean addRole(SysRoleDTO sysRoleDTO);
    boolean updateRole(SysRoleDTO sysRoleDTO);
    boolean deleteRole(SysRoleDTO sysRoleDTO);
    boolean configRolePer(SysRoleDTO sysRoleDTO);
}
