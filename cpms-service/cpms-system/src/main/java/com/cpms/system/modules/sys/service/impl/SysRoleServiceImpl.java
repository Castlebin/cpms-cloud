package com.cpms.system.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cpms.common.constant.CommonConstant;
import com.cpms.framework.common.constants.TenantConstant;
import com.cpms.framework.common.core.base.BasePageVO;
import com.cpms.framework.common.utils.CsBeanUtil;
import com.cpms.framework.common.utils.CsSecureUtil;
import com.cpms.system.modules.sys.dto.QueryRoleDTO;
import com.cpms.system.modules.sys.dto.SysRoleDTO;
import com.cpms.system.modules.sys.entity.SysRoleEntity;
import com.cpms.system.modules.sys.entity.SysRoleMenuEntity;
import com.cpms.system.modules.sys.mapper.SysRoleMapper;
import com.cpms.system.modules.sys.service.ISysRoleService;
import com.cpms.system.modules.sys.service.ISysRoleMenuService;
import com.cpms.system.modules.sys.vo.SysRoleVO;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: gulang
 * @time: 2021/8/6 17:51
 */
@Service
public class SysRoleServiceImpl  extends ServiceImpl<SysRoleMapper, SysRoleEntity> implements ISysRoleService {
    @Resource
    private SysRoleMapper sysRoleMapper;
    @Resource
    private ISysRoleMenuService sysRoleMenuService;

    @Override
    public List<SysRoleEntity> queryRoleByUserId(Long userId) {
        return sysRoleMapper.queryRoleByUserId(userId);
    }

    @Override
    public BasePageVO<SysRoleVO> listRole(QueryRoleDTO queryRoleDTO) {
        BasePageVO<SysRoleVO> listRoleVO = new BasePageVO();
        List<SysRoleVO> sysRoleVoList;
        queryRoleDTO.setTenantId(CsSecureUtil.userTenantId());
        int count = sysRoleMapper.listRoleCount(queryRoleDTO);
        if(count ==0){
            sysRoleVoList = Lists.newArrayList();
        }else{
            sysRoleVoList = sysRoleMapper.listRole(queryRoleDTO);
        }
        listRoleVO.setTotal(count);
        listRoleVO.setList(sysRoleVoList);
        return listRoleVO;
    }

    @Override
    public boolean addRole(SysRoleDTO sysRoleDTO) {
        SysRoleEntity sysRoleEntity = new SysRoleEntity();
        CsBeanUtil.copyProperties(sysRoleDTO,sysRoleEntity);
        sysRoleEntity.setTenantId(CsSecureUtil.userTenantId());
        return this.save(sysRoleEntity);
    }

    @Override
    public boolean updateRole(SysRoleDTO sysRoleDTO) {
        SysRoleEntity sysRoleEntity = new SysRoleEntity();
        CsBeanUtil.copyProperties(sysRoleDTO,sysRoleEntity);
        UpdateWrapper<SysRoleEntity> updateWrapper = Wrappers.update();
        updateWrapper.eq("role_id",sysRoleDTO.getRoleId());
        updateWrapper.eq("tenant_id", CsSecureUtil.userTenantId());
        updateWrapper.notIn("role_code", Arrays.asList(TenantConstant.SUPER_ADMINISTRATOR,TenantConstant.TENANT_ADMINISTRATOR));
        return this.update(sysRoleEntity,updateWrapper);
    }

    @Override
    public boolean deleteRole(SysRoleDTO sysRoleDTO) {
        LambdaUpdateWrapper<SysRoleEntity> updateWrapper = Wrappers.<SysRoleEntity>lambdaUpdate()
                .set(SysRoleEntity::getDelFlag, CommonConstant.DEL_FLAG_DELETED)
                .eq(SysRoleEntity::getTenantId, CsSecureUtil.userTenantId())
                .eq(SysRoleEntity::getRoleId, sysRoleDTO.getRoleId())
                .notIn(SysRoleEntity::getRoleCode,Arrays.asList(TenantConstant.SUPER_ADMINISTRATOR,TenantConstant.TENANT_ADMINISTRATOR));
        return this.update(updateWrapper);
    }

    @Override
    public boolean configRolePer(SysRoleDTO sysRoleDTO) {
        List<Long> menuIds = Arrays.stream(sysRoleDTO.getMenuIds().split(",")).mapToLong(Long::parseLong).boxed().collect(Collectors.toList());
        List<SysRoleMenuEntity> collect = menuIds.stream().map(e -> {
            SysRoleMenuEntity sysRoleMenuEntity = new SysRoleMenuEntity();
            sysRoleMenuEntity.setMenuId(e);
            sysRoleMenuEntity.setRoleId(sysRoleDTO.getRoleId());
            return sysRoleMenuEntity;
        }).collect(Collectors.toList());
        sysRoleMenuService.saveBatch(collect);
        return true;
    }
}
