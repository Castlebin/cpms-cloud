package com.cpms.system.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cpms.framework.common.core.base.BasePageVO;
import com.cpms.framework.common.utils.CsBeanUtil;
import com.cpms.framework.common.utils.CsSecureUtil;
import com.cpms.system.common.constants.SystemConstant;
import com.cpms.system.modules.sys.dto.ListDeptDTO;
import com.cpms.system.modules.sys.dto.SysDeptDTO;
import com.cpms.system.modules.sys.entity.SysDeptEntity;
import com.cpms.system.modules.sys.mapper.SysDeptMapper;
import com.cpms.system.modules.sys.service.ISysDeptService;
import com.cpms.system.modules.sys.vo.SysDeptVO;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author: gulang
 * @time: 2021/7/20 17:54
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDeptEntity> implements ISysDeptService {
    @Resource
    private SysDeptMapper sysDeptMapper;

    @Override
    public BasePageVO<SysDeptVO> listDept(ListDeptDTO listDeptDTO) {
        BasePageVO<SysDeptVO> basePageVO = new BasePageVO();
        List<SysDeptVO> list;
        int count = sysDeptMapper.listDeptCount(listDeptDTO);
        if(count ==0){
            list = Lists.newArrayList();
        }else{
            list = sysDeptMapper.listDept(listDeptDTO);
        }
        basePageVO.setTotal(count);
        basePageVO.setList(list);
        return basePageVO;
    }

    @Override
    public boolean addDept(SysDeptDTO deptDTO) {
        SysDeptEntity sysDeptEntity = new SysDeptEntity();
        CsBeanUtil.copyProperties(deptDTO,sysDeptEntity);
        sysDeptEntity.setTenantId(CsSecureUtil.userTenantId());
        return this.save(sysDeptEntity);
    }

    @Override
    public boolean updateDept(SysDeptDTO deptDTO) {
        LambdaUpdateWrapper<SysDeptEntity> updateWrapper = Wrappers.<SysDeptEntity>lambdaUpdate()
                .set(SysDeptEntity::getDeptName, deptDTO.getDeptName())
                .set(SysDeptEntity::getParentId, deptDTO.getParentId())
                .eq(SysDeptEntity::getDeptId,deptDTO.getDeptId())
                .eq(SysDeptEntity::getTenantId,CsSecureUtil.userTenantId());
        return this.update(updateWrapper);
    }

    @Override
    public boolean deleteDept(SysDeptDTO deptDTO) {
        LambdaUpdateWrapper<SysDeptEntity> updateWrapper = Wrappers.<SysDeptEntity>lambdaUpdate()
                .set(SysDeptEntity::getDelFlag, SystemConstant.DEL_FLAG_DELETED)
                .eq(SysDeptEntity::getDeptId,deptDTO.getDeptId())
                .eq(SysDeptEntity::getTenantId,CsSecureUtil.userTenantId());
        return this.update(updateWrapper);
    }
}
