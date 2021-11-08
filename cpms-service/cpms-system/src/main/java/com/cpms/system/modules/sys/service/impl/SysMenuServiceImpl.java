package com.cpms.system.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cpms.common.constant.CommonConstant;
import com.cpms.framework.common.constants.CoreCommonConstant;
import com.cpms.framework.common.core.base.BasePageVO;
import com.cpms.framework.common.core.node.NodeManager;
import com.cpms.framework.common.core.secure.TokenUserInfo;
import com.cpms.framework.common.exception.BizException;
import com.cpms.framework.common.utils.CsBeanUtil;
import com.cpms.framework.common.utils.CsDateUtil;
import com.cpms.framework.common.utils.CsSecureUtil;
import com.cpms.framework.redis.utils.CsRedisUtil;
import com.cpms.system.common.enums.SystemResponseResultEnum;
import com.cpms.system.modules.sys.dto.QueryMenuDTO;
import com.cpms.system.modules.sys.dto.SysMenuDTO;
import com.cpms.system.modules.sys.entity.SysMenuEntity;
import com.cpms.system.modules.sys.entity.SysTopMenuEntity;
import com.cpms.system.modules.sys.mapper.SysMenuMapper;
import com.cpms.system.modules.sys.service.ISysMenuService;
import com.cpms.system.modules.sys.service.ISysTopMenuService;
import com.cpms.system.modules.sys.vo.SysMenuVO;
import com.cpms.system.modules.sys.vo.SysRouteVO;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author gulang
 * @Description:
 * @time: 2021/8/7 20:53
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenuEntity> implements ISysMenuService {
    @Resource
    private SysMenuMapper sysMenuMapper;
    @Resource
    private ISysTopMenuService sysTopMenuService;

    @Override
    public SysRouteVO querySysMenuRoutes(Long topMenuId) {
         List<SysMenuEntity> menus;
        SysRouteVO sysRouteVO = new SysRouteVO();
        if( topMenuId == 0) {
              menus = getHomePageMenuRoutes();
         }else{
             // 根据顶部菜单ID获取对应的菜单显示
            menus = queryMenuByTopMenuId(topMenuId);
         }
        sysRouteVO.setMenus(NodeManager.buildTreeNode(convertVO(menus), 0L));
        sysRouteVO.setButtons(queryRoleButtons());
        return sysRouteVO;
    }

    @Override
    public boolean addMenu(SysMenuDTO sysMenuDTO) {
        SysMenuEntity sysMenuEntity = new SysMenuEntity();
        CsBeanUtil.copyProperties(sysMenuDTO,sysMenuEntity);
        return this.save(sysMenuEntity);
    }

    @Override
    public boolean updateMenu(SysMenuDTO sysMenuDTO) {
        SysMenuEntity sysMenuEntity = new SysMenuEntity();
        CsBeanUtil.copyProperties(sysMenuDTO,sysMenuEntity);
        UpdateWrapper<SysMenuEntity> updateWrapper = Wrappers.<SysMenuEntity>update();
        updateWrapper.eq("menu_id",sysMenuDTO.getMenuId());
        return this.update(sysMenuEntity, updateWrapper);
    }

    @Override
    public boolean deleteMenu(SysMenuDTO sysMenuDTO) {
        QueryWrapper<SysMenuEntity> query = Wrappers.query();
        query.eq("parent_id",sysMenuDTO.getMenuId());
        query.eq("del_flag", CommonConstant.DEL_FLAG_NOT_DELETED);
        Integer count = sysMenuMapper.selectCount(query);
        if(count > 0){
            throw new BizException(SystemResponseResultEnum.THERE_ARE_CHILD_NODES_ERROR);
        }
        SysMenuEntity sysMenuEntity = new SysMenuEntity();
        sysMenuEntity.setDelFlag(CommonConstant.DEL_FLAG_DELETED);
        sysMenuEntity.setMenuId(sysMenuDTO.getMenuId());
        return this.updateById(sysMenuEntity);
    }

    @Override
    public BasePageVO<SysMenuVO> listMenu(QueryMenuDTO listMenuDTO) {
        BasePageVO<SysMenuVO> basePageVO = new BasePageVO();
        List<SysMenuVO> list;
        int count = sysMenuMapper.listMenuCount(listMenuDTO);
        if(count == 0 ) {
            list = Lists.newArrayList();
        }else{
            List<SysMenuEntity> entities = sysMenuMapper.listMenu(listMenuDTO);
            list = NodeManager.buildTreeNode(convertVO(entities), 0L);
        }
        basePageVO.setTotal(count);
        basePageVO.setList(list);
        return basePageVO;
    }

    private List<SysMenuVO> convertVO(List<SysMenuEntity> entities){
        return  entities.stream().map(e->{
            SysMenuVO sysMenuVO = new SysMenuVO();
            CsBeanUtil.copyProperties(e,sysMenuVO);
            sysMenuVO.setId(e.getMenuId());
            return  sysMenuVO;
        }).collect(Collectors.toList());
    }

    public List<String> queryRoleButtons() {
        TokenUserInfo user = CsSecureUtil.getUser();
        List<String> buttons = sysMenuMapper.queryRoleButtons(user.getRoleIds());
        Map<String, Object> cacheMap = Maps.newHashMap();
        cacheMap.put(CoreCommonConstant.PERMISSION_KEY,StringUtils.join(buttons,","));
        long tokenExpire = user.getTokenExpire();
        long curTime = CsDateUtil.currentTimeStamp(CsDateUtil.TIME_STAMP_S);
        CsRedisUtil.hmset(CoreCommonConstant.CACHE_LOGIN_USER_INFO_KEY + user.getUserId(),cacheMap, (tokenExpire - curTime));
        return buttons;
    }

    public List<SysMenuEntity> queryMenuByTopMenuId(Long topMenuId){
        SysTopMenuEntity sysTopMenuEntity = sysTopMenuService.getBaseMapper().selectById(topMenuId);
        if(!Objects.isNull(sysTopMenuEntity) && !StringUtils.isBlank(sysTopMenuEntity.getRelationMenuIds())) {
            String[] idArr = sysTopMenuEntity.getRelationMenuIds().split(",");
            List<Long> idList = Arrays.stream(idArr).mapToLong(Long::parseLong).boxed().collect(Collectors.toList());
            List<SysMenuEntity> list = sysMenuMapper.selectBatchIds(idList);
            list.sort(Comparator.comparing(SysMenuEntity::getSort).reversed());
            return list;
        }
        return Lists.newArrayList();
    }
    /**
     * 超级管理员路由
     */
    private  List<SysMenuEntity> superAdminRoutes(){
        QueryWrapper<SysMenuEntity> wrapper=new QueryWrapper<>();
        wrapper.eq("del_flag",CommonConstant.DEL_FLAG_NOT_DELETED);
        wrapper.eq("type",CommonConstant.TYPE_MENU);
        wrapper.orderByDesc("sort");
        return sysMenuMapper.selectList(wrapper);
    }

    /**
     * 获取首页菜单
     */
    private List<SysMenuEntity> getHomePageMenuRoutes(){
        List<SysMenuEntity> menus;
        if(CsSecureUtil.isSysSuperAdmin()){
             menus = superAdminRoutes();
        }else{
            TokenUserInfo user = CsSecureUtil.getUser();
            List<Long> roleIds = user.getRoleIds();
            menus = sysMenuMapper.queryRoleMenus(roleIds,CommonConstant.TYPE_MENU);
        }
        return menus;
    }
}
