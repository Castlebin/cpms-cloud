package com.cpms.system.modules.sys.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cpms.system.modules.sys.entity.SysMenuEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author gulang
 * @Description:
 * @time: 2021/8/7 20:50
 */
@DS("master")
public interface SysMenuMapper extends BaseMapper<SysMenuEntity> {
    List<SysMenuEntity> queryRoleMenus(@Param("list") List<Long> roleIds,@Param("type") Integer type);

    List<String> queryRoleButtons(@Param("list") List<Long> roleIds);
}
