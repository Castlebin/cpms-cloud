package com.cpms.log.modules.log.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cpms.common.constant.CommonConstant;
import com.cpms.framework.common.core.base.BasePageVO;
import com.cpms.framework.common.utils.CsSecureUtil;
import com.cpms.log.common.constants.SystemConstant;
import com.cpms.log.modules.log.dto.ListLogDTO;
import com.cpms.log.modules.log.entity.SysLogEntity;
import com.cpms.log.modules.log.mapper.SysLogMapper;
import com.cpms.log.modules.log.service.ISysLogService;
import com.cpms.log.modules.log.vo.SysLogVO;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author gulang
 * @Description:
 * @time: 2021/8/21 16:07
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLogEntity> implements ISysLogService {
    @Resource
    private  SysLogMapper sysLogMapper;

    @Override
    public BasePageVO<SysLogVO> listLog(ListLogDTO listLogDTO) {
        BasePageVO<SysLogVO> basePageVO = new BasePageVO();
        List<SysLogVO> list;
        int count = sysLogMapper.listLogCount(listLogDTO);
        if(count == 0){
            list = Lists.newArrayList();
        }else{
            list = sysLogMapper.listLog(listLogDTO);
        }
        basePageVO.setTotal(count);
        basePageVO.setList(list);
        return basePageVO;
    }

    @Override
    public boolean deleteLog(Long logId) {
        LambdaUpdateWrapper<SysLogEntity> updateWrapper = Wrappers.<SysLogEntity>lambdaUpdate()
                .set(SysLogEntity::getDelFlag, CommonConstant.DEL_FLAG_DELETED)
                .eq(SysLogEntity::getLogId,logId)
                .eq(SysLogEntity::getTenantId, CsSecureUtil.userTenantId());
        return this.update(updateWrapper);
    }
}
