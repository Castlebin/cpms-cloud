package com.cpms.log.modules.log.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cpms.log.modules.log.dto.QueryLogDTO;
import com.cpms.log.modules.log.entity.SysLogEntity;
import com.cpms.log.modules.log.vo.SysLogVO;

import java.util.List;

/**
 * @author gulang
 * @Description:
 * @time: 2021/8/21 16:09
 */
public interface SysLogMapper extends BaseMapper<SysLogEntity> {
     int listLogCount(QueryLogDTO listLogDTO);
     List<SysLogVO> listLog(QueryLogDTO listLogDTO);

}
