package com.cpms.system.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cpms.common.constant.CommonConstant;
import com.cpms.framework.common.core.secure.TokenUserInfo;
import com.cpms.framework.common.utils.CsBeanUtil;
import com.cpms.framework.common.utils.CsSecureUtil;
import com.cpms.system.modules.sys.dto.SysTopMenuDTO;
import com.cpms.system.modules.sys.entity.SysTopMenuEntity;
import com.cpms.system.modules.sys.mapper.SysTopMenuMapper;
import com.cpms.system.modules.sys.service.ISysTopMenuService;
import com.cpms.system.modules.sys.vo.SysTopMenuVO;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: gulang
 * @time: 2021/8/30 14:07
 */
@Service
public class SysTopMenuServiceImpl extends ServiceImpl<SysTopMenuMapper, SysTopMenuEntity> implements ISysTopMenuService {
    @Resource
    private SysTopMenuMapper sysTopMenuMapper;


    @Override
    public List<SysTopMenuVO> getTopMenu() {
        List<SysTopMenuEntity> sysTopMenuEntities;
        if( CsSecureUtil.isSysSuperAdmin() || CsSecureUtil.isTenantAdmin()) {
            QueryWrapper<SysTopMenuEntity> wrapper=new QueryWrapper<>();
            wrapper.eq("del_flag", CommonConstant.DEL_FLAG_NOT_DELETED);
            wrapper.eq("tenant_id",CsSecureUtil.userTenantId());
            sysTopMenuEntities = sysTopMenuMapper.selectList(wrapper);
        }else{
            TokenUserInfo user = CsSecureUtil.getUser();
            List<Long> roleIds = user.getRoleIds();
            sysTopMenuEntities = sysTopMenuMapper.queryRoleTopMenus(roleIds);
        }
        return convertTopMenuToVo(sysTopMenuEntities);
    }

    @Override
    public boolean deleteTopMenu(SysTopMenuDTO sysTopMenuDTO) {
        LambdaUpdateWrapper<SysTopMenuEntity> updateWrapper = Wrappers.<SysTopMenuEntity>lambdaUpdate()
                .set(SysTopMenuEntity::getDelFlag, CommonConstant.DEL_FLAG_DELETED)
                .eq(SysTopMenuEntity::getTenantId,CsSecureUtil.userTenantId())
                .eq(SysTopMenuEntity::getTopMenuId,sysTopMenuDTO.getTopMenuId());
        return this.update(updateWrapper);
    }

    @Override
    public boolean addTopMenu(SysTopMenuDTO sysTopMenuDTO) {
        SysTopMenuEntity sysTopMenuEntity = new SysTopMenuEntity();
        CsBeanUtil.copyProperties(sysTopMenuDTO,sysTopMenuEntity);
        sysTopMenuEntity.setTenantId(CsSecureUtil.userTenantId());
        return this.save(sysTopMenuEntity);
    }

    @Override
    public boolean updateTopMenu(SysTopMenuDTO sysTopMenuDTO) {
        LambdaUpdateWrapper<SysTopMenuEntity> updateWrapper = Wrappers.<SysTopMenuEntity>lambdaUpdate()
                .set(SysTopMenuEntity::getTopMenuName, sysTopMenuDTO.getTopMenuName())
                .set(SysTopMenuEntity::getPath, sysTopMenuDTO.getPath())
                .set(SysTopMenuEntity::getSort, sysTopMenuDTO.getSort())
                .set(SysTopMenuEntity::getType, sysTopMenuDTO.getType())
                .set(SysTopMenuEntity::getIcon, sysTopMenuDTO.getIcon())
                .eq(SysTopMenuEntity::getTenantId,CsSecureUtil.userTenantId())
                .eq(SysTopMenuEntity::getTopMenuId,sysTopMenuDTO.getTopMenuId());
        return this.update(updateWrapper);
    }

    @Override
    public List<SysTopMenuVO> listTopMenu() {
        QueryWrapper<SysTopMenuEntity> wrapper=new QueryWrapper<>();
        wrapper.eq("del_flag", CommonConstant.DEL_FLAG_NOT_DELETED);
        wrapper.eq("tenant_id",CsSecureUtil.userTenantId());
        List<SysTopMenuEntity> sysTopMenuEntities = sysTopMenuMapper.selectList(wrapper);
        return convertTopMenuToVo(sysTopMenuEntities);
    }

    @Override
    public boolean configTopMenu(SysTopMenuDTO sysTopMenuDTO) {
        LambdaUpdateWrapper<SysTopMenuEntity> updateWrapper = Wrappers.<SysTopMenuEntity>lambdaUpdate()
                .set(SysTopMenuEntity::getRelationMenuIds, sysTopMenuDTO.getRelationMenuIds())
                .eq(SysTopMenuEntity::getTenantId,CsSecureUtil.userTenantId())
                .eq(SysTopMenuEntity::getTopMenuId,sysTopMenuDTO.getTopMenuId());
        return this.update(updateWrapper);
    }

    private List<SysTopMenuVO> convertTopMenuToVo(List<SysTopMenuEntity> sysTopMenuEntities){
        List<SysTopMenuVO> topMenuList;
        topMenuList = sysTopMenuEntities.stream().map(e->{
            SysTopMenuVO sysTopMenuVO = new SysTopMenuVO();
            sysTopMenuVO.setSort(e.getSort());
            sysTopMenuVO.setTopMenuId(e.getTopMenuId());
            sysTopMenuVO.setTopMenuName(e.getTopMenuName());
            sysTopMenuVO.setPath(e.getPath());
            sysTopMenuVO.setCreateTime(e.getCreateTime());
            sysTopMenuVO.setUpdateTime(e.getUpdateTime());
            sysTopMenuVO.setCreateBy(e.getCreateBy());
            sysTopMenuVO.setUpdateBy(e.getUpdateBy());
            sysTopMenuVO.setRelationMenuIds(e.getRelationMenuIds());
            sysTopMenuVO.setType(e.getType());
            sysTopMenuVO.setIcon(e.getIcon());
            return  sysTopMenuVO;
        }).sorted(Comparator.comparing(SysTopMenuVO::getSort).reversed()).collect(Collectors.toList());
        return topMenuList;
    }
}
