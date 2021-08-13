package com.cpms.system.modouls.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cpms.system.modouls.sys.entity.SysMenuEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author gulang
 * @Description:
 * @time: 2021/8/7 20:50
 */
public interface SysMenuMapper extends BaseMapper<SysMenuEntity> {
    List<SysMenuEntity> queryRoleMenus(@Param("list") List<Long> roleIds,@Param("type") Integer type);

    List<String> queryRoleButtons(@Param("list") List<Long> roleIds);
}
