package com.cpms.system.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cpms.common.constant.CommonConstant;
import com.cpms.framework.common.constants.CoreCommonConstant;
import com.cpms.framework.common.constants.TenantConstant;
import com.cpms.framework.common.core.node.NodeManager;
import com.cpms.framework.common.core.secure.TokenUserInfo;
import com.cpms.framework.common.exception.BizException;
import com.cpms.framework.common.utils.*;
import com.cpms.framework.redis.utils.CsRedisUtil;
import com.cpms.system.common.enums.RespResultEnum;
import com.cpms.system.modules.sys.dto.QueryMenuDTO;
import com.cpms.system.modules.sys.dto.SysMenuDTO;
import com.cpms.system.modules.sys.entity.SysMenuEntity;
import com.cpms.system.modules.sys.entity.SysRoleEntity;
import com.cpms.system.modules.sys.entity.SysTopMenuEntity;
import com.cpms.system.modules.sys.mapper.SysMenuMapper;
import com.cpms.system.modules.sys.service.ISysMenuService;
import com.cpms.system.modules.sys.service.ISysRoleService;
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
    private ISysRoleService sysRoleService;
    @Resource
    private ISysTopMenuService sysTopMenuService;

    @Override
    public SysRouteVO leftMenu(Long topMenuId) {
         List<SysMenuEntity> menus;
         List<String> buttons = Lists.newArrayList();
        SysRouteVO sysRouteVO = new SysRouteVO();
        if( topMenuId == 0) {
              menus = getHomePageMenuRoutes();
              // 点击首页会缓存权限信息
              buttons = queryRoleButtons();
        }else{
             // 根据顶部菜单ID获取对应的菜单显示
            menus = queryMenuByTopMenuId(topMenuId);
         }
        sysRouteVO.setMenus(NodeManager.buildTreeNode(convertVO(menus), 0L));
        sysRouteVO.setButtons(buttons);
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
            throw new BizException(RespResultEnum.THERE_ARE_CHILD_NODES_ERROR);
        }
        SysMenuEntity sysMenuEntity = new SysMenuEntity();
        sysMenuEntity.setDelFlag(CommonConstant.DEL_FLAG_DELETED);
        sysMenuEntity.setMenuId(sysMenuDTO.getMenuId());
        return this.updateById(sysMenuEntity);
    }

    @Override
    public List<SysMenuVO> listMenu(QueryMenuDTO queryMenuDTO) {
        List<SysMenuEntity> menus;
        List<SysMenuVO> list;
        menus = allSysMenu(queryMenuDTO.getType());
        list = NodeManager.buildTreeNode(convertVO(menus), 0L);
        return list;
    }

    @Override
    public List<SysMenuVO> userOwnedMenus() {
        TokenUserInfo user = CsSecureUtil.getUser();
        List<SysMenuEntity> menus;
        if(CsSecureUtil.isSysSuperAdmin()){
            menus = allSysMenu(CommonConstant.TYPE_MENU);
        }else{
            menus = sysMenuMapper.queryRoleMenusOrButtons(user.getRoleIds(),CommonConstant.TYPE_MENU);
        }
        List<SysMenuVO> list;
        list = NodeManager.buildTreeNode(convertVO(menus), 0L);
        return list;
    }

    @Override
    public List<SysMenuVO> tenantOwnedMenus() {
        List<SysMenuEntity> menus;
        List<SysMenuVO> list;
        if(CsSecureUtil.isHeadquarters()) {
            menus = allSysMenu(null);
        }else{
            menus = getTenantMenuAndButton(CsSecureUtil.userTenantId());
        }
        list =  NodeManager.buildTreeNode(convertVO(menus), 0L);
        return list;
    }

    private List<SysMenuEntity> getTenantMenuAndButton(Long tenantId){
        List<SysMenuEntity> menus = Lists.newArrayList();
        QueryWrapper<SysRoleEntity> query = Wrappers.query();
        query.select("role_id");
        query.eq("tenant_id",tenantId);
        query.eq("role_code", TenantConstant.TENANT_ADMINISTRATOR);
        SysRoleEntity sysRoleEntity = sysRoleService.getBaseMapper().selectOne(query);
        if(!Objects.isNull(sysRoleEntity)){
            menus = sysMenuMapper.queryRoleMenusOrButtons(Arrays.asList(sysRoleEntity.getRoleId()),null);
        }
        return menus;
    }


    @Override
    public List<SysMenuVO> selectMenuByTenantId(Long tenantId) {
        List<SysMenuVO> list;
        List<SysMenuEntity> menus = getTenantMenuAndButton(tenantId);
        list = convertVO(menus);
        return list;
    }

    private List<SysMenuVO> convertVO(List<SysMenuEntity> entities){
        List<SysMenuVO> list = Lists.newArrayList();
        if(!CsCollectionUtil.isEmpty(entities)) {
            list =  entities.stream().map(e->{
                SysMenuVO sysMenuVO = new SysMenuVO();
                CsBeanUtil.copyProperties(e,sysMenuVO);
                sysMenuVO.setId(e.getMenuId());
                return  sysMenuVO;
            }).collect(Collectors.toList());
        }
        return list;
    }

    private List<String> queryRoleButtons() {
        TokenUserInfo user = CsSecureUtil.getUser();
        List<String> buttons = Lists.newArrayList();
        List<SysMenuEntity> SysMenuEntitys = sysMenuMapper.queryRoleMenusOrButtons(user.getRoleIds(),CommonConstant.TYPE_BUTTON);
        if(!CsCollectionUtil.isEmpty(SysMenuEntitys)){
            Map<String, Object> cacheMap = Maps.newHashMap();
            buttons = SysMenuEntitys.stream().map(SysMenuEntity::getCode).collect(Collectors.toList());
            String strButton = CsStringUtil.join(buttons,",");
            cacheMap.put(CoreCommonConstant.PERMISSION_KEY,strButton);
            long tokenExpire = user.getTokenExpire();
            long curTime = CsDateUtil.currentTimeStamp(CsDateUtil.TIME_STAMP_S);
            CsRedisUtil.hmset(CoreCommonConstant.CACHE_LOGIN_USER_INFO_KEY + user.getUserId(),cacheMap, (tokenExpire - curTime));
        }
        return buttons;
    }

    public List<SysMenuEntity> queryMenuByTopMenuId(Long topMenuId){
        List<SysMenuEntity> list = Lists.newArrayList();
        SysTopMenuEntity sysTopMenuEntity = sysTopMenuService.getBaseMapper().selectById(topMenuId);
        if(!Objects.isNull(sysTopMenuEntity) && !StringUtils.isBlank(sysTopMenuEntity.getRelationMenuIds())) {
            List<Long> idList = Arrays.stream(sysTopMenuEntity.getRelationMenuIds().split(",")).mapToLong(Long::parseLong).boxed().collect(Collectors.toList());
            List<Long> topMenuIds = idList;
            if(!CsSecureUtil.isSysSuperAdmin()) {
                // 获取当前用户所属角色拥有的实际权限，排除掉权限之外顶部对应的菜单
                List<SysMenuEntity> roleMenus = sysMenuMapper.queryRoleMenusOrButtons(CsSecureUtil.getUser().getRoleIds(),CommonConstant.TYPE_MENU);
                List<Long> roleMenuIds = roleMenus.stream().map(SysMenuEntity::getMenuId).collect(Collectors.toList());
                topMenuIds = idList.stream().filter(e -> roleMenuIds.contains(e)).collect(Collectors.toList());
            }
            if(!CsCollectionUtil.isEmpty(topMenuIds)) {
                list = sysMenuMapper.selectBatchIds(topMenuIds);
                list.sort(Comparator.comparing(SysMenuEntity::getSort).reversed());
            }
        }
        return list;
    }

    /**
     * 所有系统菜单
     * @Param type 菜单类型 0：菜单 ，1：按钮 ， null:查询菜单和按钮
     */
    private  List<SysMenuEntity> allSysMenu(Integer type){
        QueryWrapper<SysMenuEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("del_flag",CommonConstant.DEL_FLAG_NOT_DELETED);
        if(!Objects.isNull(type)) {
            wrapper.eq("type",type);
        }
        wrapper.orderByDesc("sort");
        return sysMenuMapper.selectList(wrapper);
    }

    /**
     * 获取首页菜单
     */
    private List<SysMenuEntity> getHomePageMenuRoutes(){
        List<SysMenuEntity> menus;
        if(CsSecureUtil.isSysSuperAdmin()){
             menus = allSysMenu(CommonConstant.TYPE_MENU);
        }else{
            TokenUserInfo user = CsSecureUtil.getUser();
            List<Long> roleIds = user.getRoleIds();
            menus = sysMenuMapper.queryRoleMenusOrButtons(roleIds,CommonConstant.TYPE_MENU);
        }
        return menus;
    }
}
