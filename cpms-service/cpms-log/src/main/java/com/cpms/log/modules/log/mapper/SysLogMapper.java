package com.cpms.log.modules.log.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cpms.log.modules.log.entity.SysLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author gulang
 * @Description:
 * @time: 2021/8/21 16:09
 */
@DS("master")
public interface SysLogMapper extends BaseMapper<SysLogEntity> {
}
