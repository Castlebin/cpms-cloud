package com.cpms.system.modules.sys.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cpms.system.modules.sys.dto.QueryRoleDTO;
import com.cpms.system.modules.sys.entity.SysRoleEntity;
import com.cpms.system.modules.sys.vo.SysRoleVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description:
 * @author: gulang
 * @time: 2021/8/6 17:10
 */
@DS("master")
public interface SysRoleMapper extends BaseMapper<SysRoleEntity> {
    List<SysRoleEntity> queryRoleByUserId(@Param("userId") Long userId);

    int listRoleCount(QueryRoleDTO listRoleDTO);

    List<SysRoleVO> listRole(QueryRoleDTO listRoleDTO);

    List<Long> selectMenuIdByRoleId(Long roleId);
}
