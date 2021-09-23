package com.cpms.system.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cpms.framework.common.core.base.BasePageVO;
import com.cpms.framework.common.utils.CsBeanUtil;
import com.cpms.system.common.constants.SystemConstant;
import com.cpms.system.modules.sys.dto.ListTenantDTO;
import com.cpms.system.modules.sys.dto.SysTenantDTO;
import com.cpms.system.modules.sys.entity.SysDeptEntity;
import com.cpms.system.modules.sys.entity.SysTenantEntity;
import com.cpms.system.modules.sys.mapper.SysTenantMapper;
import com.cpms.system.modules.sys.service.ISysDeptService;
import com.cpms.system.modules.sys.service.ISysTenantService;
import com.cpms.system.modules.sys.vo.SysTenantVO;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author: gulang
 * @time: 2021/9/22 11:28
 */
@Service
public class SysTenantServiceImpl extends ServiceImpl<SysTenantMapper, SysTenantEntity> implements ISysTenantService {
    @Resource
    private SysTenantMapper sysTenantMapper;

    @Resource
    private ISysDeptService sysDeptService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addTenant(SysTenantDTO tenantDTO) {
        SysTenantEntity sysTenantEntity = new SysTenantEntity();
        CsBeanUtil.copyProperties(tenantDTO,sysTenantEntity);
        this.save(sysTenantEntity);
        SysDeptEntity sysDeptEntity = new SysDeptEntity();
        sysDeptEntity.setTenantId(sysTenantEntity.getTenantId());
        sysDeptEntity.setDeptName(tenantDTO.getTenantName());
        sysDeptEntity.setParentId(0L);
        sysDeptService.save(sysDeptEntity);
        return true;
    }

    @Override
    public boolean updateTenant(SysTenantDTO tenantDTO) {
        SysTenantEntity sysTenantEntity = new SysTenantEntity();
        CsBeanUtil.copyProperties(tenantDTO,sysTenantEntity);
        return this.updateById(sysTenantEntity);
    }

    @Override
    public boolean deleteTenant(SysTenantDTO tenantDTO) {
        LambdaUpdateWrapper<SysTenantEntity> updateWrapper = Wrappers.<SysTenantEntity>lambdaUpdate()
                .set(SysTenantEntity::getDelFlag, SystemConstant.DEL_FLAG_DELETED)
                .eq(SysTenantEntity::getTenantId,tenantDTO.getTenantId());
        return this.update(updateWrapper);
    }

    @Override
    public BasePageVO<SysTenantVO> listTenant(ListTenantDTO listTenantDTO) {
        BasePageVO<SysTenantVO> basePageVO = new BasePageVO();
        List<SysTenantVO> list;
        int count = sysTenantMapper.listTenantCount(listTenantDTO);
        if(count == 0){
            list = Lists.newArrayList();
        }else{
            list = sysTenantMapper.listTenant(listTenantDTO);
        }
        basePageVO.setTotal(count);
        basePageVO.setList(list);
        return basePageVO;
    }


}
