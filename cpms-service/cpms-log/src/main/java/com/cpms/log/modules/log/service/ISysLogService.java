package com.cpms.log.modules.log.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cpms.framework.common.core.base.BasePageVO;
import com.cpms.log.modules.log.dto.QueryLogDTO;
import com.cpms.log.modules.log.entity.SysLogEntity;
import com.cpms.log.modules.log.vo.SysLogVO;

/**
 * @author gulang
 * @Description:
 * @time: 2021/8/21 16:05
 */
public interface ISysLogService extends IService<SysLogEntity> {
    BasePageVO<SysLogVO> listLog(QueryLogDTO listLogDTO);
    boolean deleteLog( Long logId);
}
