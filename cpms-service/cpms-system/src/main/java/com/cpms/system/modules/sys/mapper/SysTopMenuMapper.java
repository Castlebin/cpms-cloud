package com.cpms.system.modules.sys.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cpms.system.modules.sys.entity.SysTopMenuEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description:
 * @author: gulang
 * @time: 2021/8/30 14:03
 */
@DS("master")
public interface SysTopMenuMapper extends BaseMapper<SysTopMenuEntity> {
    List<SysTopMenuEntity> queryRoleTopMenus(@Param("list") List<Long> roleIds);
}
