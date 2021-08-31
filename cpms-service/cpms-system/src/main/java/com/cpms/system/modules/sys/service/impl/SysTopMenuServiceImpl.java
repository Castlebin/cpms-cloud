package com.cpms.system.modules.sys.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cpms.common.core.secure.TokenUserInfo;
import com.cpms.common.utils.CsSecureUtil;
import com.cpms.system.modules.sys.entity.SysMenuEntity;
import com.cpms.system.modules.sys.entity.SysTopMenuEntity;
import com.cpms.system.modules.sys.mapper.SysTopMenuMapper;
import com.cpms.system.modules.sys.service.ISysTopMenuService;
import com.cpms.system.modules.sys.vo.SysTopMenuVO;
import com.google.common.collect.Lists;
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
@DS("master")
public class SysTopMenuServiceImpl extends ServiceImpl<SysTopMenuMapper, SysTopMenuEntity> implements ISysTopMenuService {
    @Resource
    private SysTopMenuMapper sysTopMenuMapper;


    @Override
    public List<SysTopMenuVO> getTopMenu() {
        List<SysTopMenuVO> topMenuList;
        List<SysTopMenuEntity> sysTopMenuEntities;
        if(CsSecureUtil.isSuperAdmin()) {
            QueryWrapper<SysTopMenuEntity> wrapper=new QueryWrapper<>();
            wrapper.eq("del_flag",0);
            wrapper.eq("tenant_id",CsSecureUtil.getUser().getTenantId());
            sysTopMenuEntities = sysTopMenuMapper.selectList(wrapper);
        }else{
            TokenUserInfo user = CsSecureUtil.getUser();
            List<Long> roleIds = user.getRoleIds();
            sysTopMenuEntities = sysTopMenuMapper.queryRoleTopMenus(roleIds);
        }
        topMenuList = sysTopMenuEntities.stream().map(e->{
            SysTopMenuVO sysTopMenuVO = new SysTopMenuVO();
            sysTopMenuVO.setSort(e.getSort());
            sysTopMenuVO.setTopMenuId(e.getTopMenuId());
            sysTopMenuVO.setTopMenuName(e.getTopMenuName());
            sysTopMenuVO.setPath(e.getPath());
            return  sysTopMenuVO;
        }).sorted(Comparator.comparing(SysTopMenuVO::getSort)).collect(Collectors.toList());
        return topMenuList;
    }
}
