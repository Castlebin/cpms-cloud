package com.cpms.log.modules.log.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cpms.log.modules.log.dto.ListLogDTO;
import com.cpms.log.modules.log.entity.SysLogEntity;
import com.cpms.log.modules.log.vo.SysLogVO;

import java.util.List;

/**
 * @author gulang
 * @Description:
 * @time: 2021/8/21 16:09
 */
@DS("master")
public interface SysLogMapper extends BaseMapper<SysLogEntity> {
     int listLogCount(ListLogDTO listLogDTO);
     List<SysLogVO> listLog(ListLogDTO listLogDTO);

}
