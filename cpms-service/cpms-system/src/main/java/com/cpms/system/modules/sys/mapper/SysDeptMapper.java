package com.cpms.system.modules.sys.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cpms.system.modules.sys.entity.SysDeptEntity;

/**
 * @description:
 * @author: gulang
 * @time: 2021/7/20 17:51
 */
@DS("master")
public interface SysDeptMapper extends BaseMapper<SysDeptEntity> {
}
