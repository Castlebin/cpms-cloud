package com.cpms.system.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cpms.common.constant.CommonConstant;
import com.cpms.framework.common.constants.TenantConstant;
import com.cpms.framework.common.core.base.BasePageVO;
import com.cpms.framework.common.enums.GlobalResponseResultEnum;
import com.cpms.framework.common.exception.BizException;
import com.cpms.framework.common.utils.CsBeanUtil;
import com.cpms.framework.common.utils.CsSecureUtil;
import com.cpms.framework.mybatis.utils.CsPageRespUtil;
import com.cpms.system.modules.sys.dto.QueryRoleDTO;
import com.cpms.system.modules.sys.dto.SysRoleDTO;
import com.cpms.system.modules.sys.entity.SysRoleEntity;
import com.cpms.system.modules.sys.entity.SysRoleMenuEntity;
import com.cpms.system.modules.sys.mapper.SysRoleMapper;
import com.cpms.system.modules.sys.service.ISysRoleService;
import com.cpms.system.modules.sys.service.ISysRoleMenuService;
import com.cpms.system.modules.sys.vo.SysRoleVO;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
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
        List<SysRoleVO> list = Lists.newArrayList();
        if(Objects.isNull(queryRoleDTO.getTenantId())) {
            queryRoleDTO.setTenantId(CsSecureUtil.userTenantId());
        }else{
            queryRoleDTO.setTenantId(queryRoleDTO.getTenantId());
        }
        int count = sysRoleMapper.listRoleCount(queryRoleDTO);
        if(count > 0){
            list = sysRoleMapper.listRole(queryRoleDTO);
        }
        return CsPageRespUtil.buildPageResult(list,count,SysRoleVO.class);
    }

    @Override
    public boolean addRole(SysRoleDTO sysRoleDTO) {
        SysRoleEntity sysRoleEntity = new SysRoleEntity();
        sysRoleDTO.setRoleCode(sysRoleDTO.getRoleCode().toUpperCase());
        if(sysRoleDTO.getRoleCode().equals(TenantConstant.TENANT_ADMINISTRATOR)){
            throw new BizException(GlobalResponseResultEnum.HANDEL_FAIL.getCode(),"角色编码不被允许！！！");
        }
        CsBeanUtil.copyProperties(sysRoleDTO,sysRoleEntity);
        sysRoleEntity.setTenantId(CsSecureUtil.userTenantId());
        return this.save(sysRoleEntity);
    }

    @Override
    public boolean updateRole(SysRoleDTO sysRoleDTO) {
        SysRoleEntity sysRoleEntity = new SysRoleEntity();
        CsBeanUtil.copyProperties(sysRoleDTO,sysRoleEntity,"roleCode");
        UpdateWrapper<SysRoleEntity> updateWrapper = Wrappers.update();
        updateWrapper.eq("role_id",sysRoleDTO.getRoleId());
        updateWrapper.eq("tenant_id", CsSecureUtil.userTenantId());
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
    @Transactional(rollbackFor = Exception.class)
    public boolean configRolePer(SysRoleDTO sysRoleDTO) {
        saveRoleMenu(sysRoleDTO.getMenuIds(),sysRoleDTO.getRoleId());
        return true;
    }

    /**
     * 保存角色菜单关联数据
     * @param menuIds 菜单id，逗号分割的字符串
     * @param roleId  角色id
     * @return
     */
    @Override
    public boolean saveRoleMenu(String menuIds,Long roleId){
        // 先删除role对应的数据，在重新添加
        Map<String,Object> wrap = Maps.newHashMap();
        wrap.put("role_id",roleId);
        sysRoleMenuService.removeByMap(wrap);
        List<Long> menuIdList = Arrays.stream(menuIds.split(",")).mapToLong(Long::parseLong).boxed().collect(Collectors.toList());
        List<SysRoleMenuEntity> collect = menuIdList.stream().map(e -> {
            SysRoleMenuEntity sysRoleMenuEntity = new SysRoleMenuEntity();
            sysRoleMenuEntity.setMenuId(e);
            sysRoleMenuEntity.setRoleId(roleId);
            return sysRoleMenuEntity;
        }).collect(Collectors.toList());
        sysRoleMenuService.saveBatch(collect);
        return  true;
    }
}
