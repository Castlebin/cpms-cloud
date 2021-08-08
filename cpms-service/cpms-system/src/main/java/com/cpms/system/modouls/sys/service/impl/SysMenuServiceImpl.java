package com.cpms.system.modouls.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cpms.common.utils.CsSecureUtil;
import com.cpms.system.modouls.sys.entity.SysMenuEntity;
import com.cpms.system.modouls.sys.mapper.SysMenuMapper;
import com.cpms.system.modouls.sys.service.SysMenuService;
import com.cpms.system.modouls.sys.vo.SysMenuVO;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author gulang
 * @Description:
 * @time: 2021/8/7 20:53
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenuEntity> implements SysMenuService {
    @Resource
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenuVO> querySysMenuRoutes(Long topMenuId) {
         List<SysMenuEntity> menus = Lists.newArrayList();
         if( Objects.isNull(topMenuId)) {
             if(CsSecureUtil.isSuperAdmin()){
                 superAdminRoutes(menus);
             }


         }else{
             // todo
         }

        return null;
    }

    /**
     * 超级管理员路由
     * @param menus
     */
    private void superAdminRoutes(List<SysMenuEntity> menus){
        QueryWrapper<SysMenuEntity> wrapper=new QueryWrapper<>();
        wrapper.eq("del_flag",0);
        wrapper.eq("type",0);
        menus = sysMenuMapper.selectList(wrapper);
    }

    /**
     * 管理员路由
     * @param menus
     */
    private void adminRoutes(List<SysMenuEntity> menus){

    }
}
