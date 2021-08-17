package com.cpms.system.modules.sys.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cpms.common.constant.TokenConstant;
import com.cpms.common.core.secure.TokenUserInfo;
import com.cpms.common.utils.CsJwtUtil;
import com.cpms.common.utils.CsSecureUtil;
import com.cpms.framework.redis.utils.CsRedisUtil;
import com.cpms.system.modules.sys.entity.SysMenuEntity;
import com.cpms.system.modules.sys.mapper.SysMenuMapper;
import com.cpms.system.modules.sys.service.SysMenuService;
import com.cpms.system.modules.sys.vo.SysMenuVO;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.*;

/**
 * @author gulang
 * @Description:
 * @time: 2021/8/7 20:53
 */
@Service
@DS("master")
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenuEntity> implements SysMenuService {
    @Resource
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenuVO> querySysMenuRoutes(Long topMenuId) {
         List<SysMenuEntity> menus;
         if( Objects.isNull(topMenuId)) {
              menus = getHomePageMenuRoutes();
         }else{
             // todo 根据顶部菜单ID获取对应的菜单显示
             menus = null;
         }
        return parseMenuTree(menus);
    }

    @Override
    public List<String> queryRoleButtons() {
        TokenUserInfo user = CsSecureUtil.getUser();
        List<String> buttons = sysMenuMapper.queryRoleButtons(user.getRoleIds());
        Map<String, Object> cacheMap = Maps.newHashMap();
        cacheMap.put(TokenConstant.PERMISSION_KEY,StringUtils.join(buttons,","));
        CsRedisUtil.hmset(TokenConstant.CACHE_LOGIN_USER_INFO_KEY + user.getUserId(),cacheMap, CsJwtUtil.getTokenExpire());
        return buttons;
    }

    /**
     * 超级管理员路由
     */
    private  List<SysMenuEntity> superAdminRoutes(){
        QueryWrapper<SysMenuEntity> wrapper=new QueryWrapper<>();
        wrapper.eq("del_flag",0);
        wrapper.eq("type",0);
        return sysMenuMapper.selectList(wrapper);
    }

    /**
     * 获取首页菜单
     */
    private List<SysMenuEntity> getHomePageMenuRoutes(){
        List<SysMenuEntity> menus;
        if(CsSecureUtil.isSuperAdmin()){
             menus = superAdminRoutes();
        }else{
            TokenUserInfo user = CsSecureUtil.getUser();
            List<Long> roleIds = user.getRoleIds();
            menus = sysMenuMapper.queryRoleMenus(roleIds,0);
        }
        return menus;
    }

    public List<SysMenuVO> parseMenuTree(List<SysMenuEntity> list) {
        List<SysMenuVO> result = new ArrayList<>();
        //1.获取所有父节点
        for (SysMenuEntity menu : list) {
            if (Objects.equals(0L,menu.getParentId())) {
                SysMenuVO vo = new SysMenuVO();
                BeanUtils.copyProperties(menu,vo);
                result.add(vo);
            }
        }
        //2.获取递归子节点
        result.forEach(parent->menuChild(parent, list));
        result.sort(Comparator.comparing(SysMenuVO::getSort));
        return result;
    }

    public void menuChild(SysMenuVO parent, List<SysMenuEntity> list) {
        for (SysMenuEntity child : list) {
            if (Objects.equals(parent.getMenuId(),child.getParentId())) {
                SysMenuVO vo = new SysMenuVO();
                BeanUtils.copyProperties(child,vo);
                if (parent.getChildren() == null) {
                    parent.setChildren(new ArrayList<>());
                }
                parent.getChildren().add(vo);
               menuChild(vo, list);
            }
        }
    }
}
