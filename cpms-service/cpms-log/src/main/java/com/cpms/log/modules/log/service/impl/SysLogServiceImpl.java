package com.cpms.log.modules.log.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cpms.log.modules.log.entity.SysLogEntity;
import com.cpms.log.modules.log.mapper.SysLogMapper;
import com.cpms.log.modules.log.service.ISysLogService;
import org.springframework.stereotype.Service;

/**
 * @author gulang
 * @Description:
 * @time: 2021/8/21 16:07
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLogEntity> implements ISysLogService {
}
