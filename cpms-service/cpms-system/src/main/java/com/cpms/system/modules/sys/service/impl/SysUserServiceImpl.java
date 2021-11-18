package com.cpms.system.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
import com.cpms.framework.common.utils.CsGenerateIdUtil;
import com.cpms.framework.common.utils.CsSecureUtil;
import com.cpms.system.api.modules.sys.bo.SysUserLoginBO;
import com.cpms.system.api.modules.sys.dto.SysUserLginDTO;
import com.cpms.system.common.enums.SystemResponseResultEnum;
import com.cpms.system.modules.sys.dto.PersonalInfoDTO;
import com.cpms.system.modules.sys.dto.QueryUserDTO;
import com.cpms.system.modules.sys.dto.ResetPasswordDTO;
import com.cpms.system.modules.sys.dto.SysUserDTO;
import com.cpms.system.modules.sys.entity.*;
import com.cpms.system.modules.sys.mapper.SysTenantMapper;
import com.cpms.system.modules.sys.mapper.SysUserMapper;
import com.cpms.system.modules.sys.service.ISysDeptService;
import com.cpms.system.modules.sys.service.ISysRoleService;
import com.cpms.system.modules.sys.service.ISysUserService;
import com.cpms.system.modules.sys.service.ISysRoleUserService;
import com.cpms.system.modules.sys.vo.SysUserDetailVO;
import com.cpms.system.modules.sys.vo.SysUserVO;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: gulang
 * @time: 2021/7/16 19:36
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserEntity> implements ISysUserService {
    @Resource
    private  SysUserMapper sysUserMapper;
    @Resource
    private SysTenantMapper sysTenantMapper;
    @Resource
    private ISysRoleUserService sysRoleUserService;
    @Resource
    private ISysRoleService sysRoleService;
    @Resource
    private ISysDeptService sysDeptService;

    @Override
    public BasePageVO<SysUserVO> listUser(QueryUserDTO queryUserDTO) {
        BasePageVO<SysUserVO> listUserVO = new BasePageVO();
        List<SysUserVO> sysUserVoList;
        if(!CsSecureUtil.isHeadquarters() || Objects.isNull(queryUserDTO.getDeptId())) {
            queryUserDTO.setTenantId(CsSecureUtil.userTenantId());
        }
        List<Long> allChildDepts = Lists.newArrayList();
        if(!Objects.isNull(queryUserDTO.getDeptId())) {
            List<SysDeptEntity> allDept = sysDeptService.findTenantDeptNodes(queryUserDTO.getDeptId(), queryUserDTO.getTenantId());
            this.findChildNodes(allDept,queryUserDTO.getDeptId(),allChildDepts);
            allChildDepts.add(queryUserDTO.getDeptId());
        }
        int count = sysUserMapper.listUserCount(queryUserDTO,allChildDepts);
        if(count == 0 ){
            sysUserVoList = Lists.newArrayList();
        }else {
            sysUserVoList = sysUserMapper.listUser(queryUserDTO,allChildDepts);
        }
        listUserVO.setTotal(count);
        listUserVO.setList(sysUserVoList);
        return listUserVO;
    }

    @Override
    public SysUserDetailVO getUserDetail(SysUserDTO userDTO) {
        QueryWrapper<SysUserEntity> query = Wrappers.query();
        query.select("user_avatar","user_sex","user_birthday","user_mobile","user_real_name","user_name");
        query.eq("user_id",userDTO.getUserId());
        if(!CsSecureUtil.isHeadquarters()) {
            query.eq("tenant_id",CsSecureUtil.userTenantId());
        }
        SysUserEntity sysUserEntity = sysUserMapper.selectOne(query);
        SysUserDetailVO sysUserDetailVO = new SysUserDetailVO();
        if(Objects.isNull(sysUserEntity)) {
            return sysUserDetailVO;
        }
        CsBeanUtil.copyProperties(sysUserEntity,sysUserDetailVO);
        return sysUserDetailVO;
    }

    @Override
    public SysUserDetailVO checkUserInfo(SysUserDTO userDTO) {
        SysUserDetailVO sysUserDetailVO = getUserDetail(userDTO);
        return  sysUserDetailVO;

    }

    private void findChildNodes(List<SysDeptEntity> list,Long parentId,List<Long> allChildDepts){
        for (SysDeptEntity sysDeptEntity : list) {
            if (Objects.equals(parentId,sysDeptEntity.getParentId())) {
                 allChildDepts.add(sysDeptEntity.getDeptId());
                 findChildNodes(list, sysDeptEntity.getDeptId(),allChildDepts);
            }
        }
    }

    @Override
    public SysUserLoginBO querySysUserInfo(SysUserLginDTO sysUserLginDTO) {
        return sysUserMapper.querySysUserInfo(sysUserLginDTO);
    }

    @Override
    public boolean deleteUser(SysUserDTO userDTO) {
        Long tenantId = CsSecureUtil.userTenantId();
        List<String> roleCodeList = sysRoleUserService.queryRoleCodeByUserId(userDTO.getUserId());
        if(roleCodeList.contains(TenantConstant.SUPER_ADMINISTRATOR) || roleCodeList.contains(TenantConstant.TENANT_ADMINISTRATOR)) {
            throw new BizException(GlobalResponseResultEnum.OPERATING_SYS_DATA_CANNOT_ERROR);
        }
        if(CsSecureUtil.isHeadquarters()) {
            if(!Objects.isNull(userDTO.getTenantId())) {
                tenantId = userDTO.getTenantId();
            }
        }
        LambdaUpdateWrapper<SysUserEntity> updateWrapper = Wrappers.<SysUserEntity>lambdaUpdate()
                .set(SysUserEntity::getDelFlag, CommonConstant.DEL_FLAG_DELETED)
                .eq(SysUserEntity::getUserId, userDTO.getUserId())
                .eq(SysUserEntity::getTenantId,tenantId);
        return this.update(updateWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addUser(SysUserDTO userDTO) {
        Integer count = sysUserMapper.selectCount(Wrappers.<SysUserEntity>query().lambda()
                .eq(SysUserEntity::getUserAccount, userDTO.getUserAccount()));
        if( count > 0){
            throw new BizException(SystemResponseResultEnum.USER_ALREADY_EXISTS_ERROR);
        }
        SysUserEntity sysUserEntity = new SysUserEntity();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        userDTO.setUserPassword(bCryptPasswordEncoder.encode(userDTO.getUserPassword()));
        BeanUtils.copyProperties(userDTO,sysUserEntity);
        sysUserEntity.setTenantId(CsSecureUtil.userTenantId());
        if(CsSecureUtil.isHeadquarters()) {
            if(!Objects.isNull(userDTO.getTenantId())) {
                sysUserEntity.setTenantId(userDTO.getTenantId());
            }
        }
        this.save(sysUserEntity);
        addBatchRole(userDTO,sysUserEntity);
        return true ;
    }

    private void addBatchRole(SysUserDTO userDTO,SysUserEntity sysUserEntity){
        QueryWrapper<SysRoleEntity> queryRole = Wrappers.query();
        queryRole.select("role_id");
        queryRole.eq("tenant_id",userDTO.getTenantId());
        queryRole.in("role_code",TenantConstant.SUPER_ADMINISTRATOR,TenantConstant.TENANT_ADMINISTRATOR);
        List<Long> sysRoleIds = sysRoleService.list(queryRole).stream().map(SysRoleEntity::getRoleId).collect(Collectors.toList());
        // 为了安全，排除掉前端传的系统角色
        List<Long> roleIdList = userDTO.getRoleIds().stream().mapToLong(Long::parseLong).boxed().filter(e->!sysRoleIds.contains(e)).collect(Collectors.toList());
        List<SysRoleUserEntity> roleUserEntityList = roleIdList.stream().map(e -> {
            SysRoleUserEntity sysRoleUserEntity = new SysRoleUserEntity();
            sysRoleUserEntity.setUserId(sysUserEntity.getUserId());
            sysRoleUserEntity.setRoleId(e);
            return sysRoleUserEntity;
        }).collect(Collectors.toList());
        sysRoleUserService.saveBatch(roleUserEntityList);
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateUser(SysUserDTO userDTO) {
        SysUserEntity sysUserEntity = new SysUserEntity();
        sysUserEntity.setDeptId(userDTO.getDeptId());
        sysUserEntity.setUserName(userDTO.getUserName());
        sysUserEntity.setUserRealName(userDTO.getUserRealName());
        sysUserEntity.setUserSex(userDTO.getUserSex());
        sysUserEntity.setUserMobile(userDTO.getUserMobile());
        sysUserEntity.setPostId(userDTO.getPostId());
        sysUserEntity.setUserBirthday(userDTO.getUserBirthday());
        sysUserEntity.setUserId(userDTO.getUserId());
        UpdateWrapper<SysUserEntity> updateWrapper = Wrappers.update();
        updateWrapper.eq("user_id",userDTO.getUserId());
        Long tenantId = CsSecureUtil.userTenantId();
        if(CsSecureUtil.isHeadquarters()) {
            if(!Objects.isNull(userDTO.getTenantId())) {
                tenantId = userDTO.getTenantId();
            }
        }
        updateWrapper.eq("tenant_id", tenantId);
        this.update(sysUserEntity,updateWrapper);
        List<String> roleCodeList = sysRoleUserService.queryRoleCodeByUserId(userDTO.getUserId());
        if(!roleCodeList.contains(TenantConstant.SUPER_ADMINISTRATOR) && !roleCodeList.contains(TenantConstant.TENANT_ADMINISTRATOR)) {
            // 删除用户所有的角色
            LambdaUpdateWrapper<SysRoleUserEntity> delWrapper = Wrappers.<SysRoleUserEntity>lambdaUpdate()
                    .eq(SysRoleUserEntity::getUserId, userDTO.getUserId());
            sysRoleUserService.getBaseMapper().delete(delWrapper);
            // 重新添加更新后的角色
            addBatchRole(userDTO,sysUserEntity);
        }
        return true;
    }


    @Override
    public boolean resetPassword(ResetPasswordDTO resetPasswordDTO) {
        Long userId = CsSecureUtil.getUser().getUserId();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        SysUserEntity sysUserEntity = sysUserMapper.selectOne(Wrappers.<SysUserEntity>query().lambda().eq(SysUserEntity::getUserId, userId));
        if(!bCryptPasswordEncoder.matches(resetPasswordDTO.getOldPassword(),sysUserEntity.getUserPassword())) {
            throw new BizException(SystemResponseResultEnum.ORIGINAL_PASSWORD_NOT_MATCH_ERROR);
        }
        LambdaUpdateWrapper<SysUserEntity> updateWrapper = Wrappers.<SysUserEntity>lambdaUpdate()
                .set(SysUserEntity::getUserPassword, bCryptPasswordEncoder.encode(resetPasswordDTO.getNewPassword()))
                .eq(SysUserEntity::getUserId, userId)
                .eq(SysUserEntity::getTenantId, CsSecureUtil.userTenantId());
        return this.update(updateWrapper);
    }

    @Override
    public boolean modifiedPassword(ResetPasswordDTO resetPasswordDTO) {
        if(Objects.isNull(resetPasswordDTO.getUserId())|| Objects.isNull(resetPasswordDTO.getNewPassword())) {
            throw new BizException(GlobalResponseResultEnum.PARAM_MISS_ERROR);
        }
        List<String> roleCodeList = sysRoleUserService.queryRoleCodeByUserId(resetPasswordDTO.getUserId());
        if(roleCodeList.contains(TenantConstant.SUPER_ADMINISTRATOR)) {
            throw new BizException(GlobalResponseResultEnum.OPERATING_SYS_DATA_CANNOT_ERROR);
        }
        if(!CsSecureUtil.isHeadquarters() && roleCodeList.contains(TenantConstant.TENANT_ADMINISTRATOR)){
            throw new BizException(GlobalResponseResultEnum.OPERATING_SYS_DATA_CANNOT_ERROR);
        }

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        UpdateWrapper<SysUserEntity> updateWrapper = Wrappers.update();
        SysUserEntity sysUserEntity = new SysUserEntity();
        sysUserEntity.setUserPassword(bCryptPasswordEncoder.encode(resetPasswordDTO.getNewPassword()));
        updateWrapper.eq("user_id",resetPasswordDTO.getUserId());
        if(!CsSecureUtil.isHeadquarters()) {
            updateWrapper.eq("tenant_id", CsSecureUtil.userTenantId());
        }
        return this.update(sysUserEntity,updateWrapper);
    }

    @Override
    public int userCount(Long tenantId) {
        return sysUserMapper.selectCount(Wrappers.<SysUserEntity>query().lambda().eq(SysUserEntity::getTenantId, tenantId));
    }

    @Override
    public String generateAccount(SysUserDTO userDTO) {
        if(Objects.isNull(userDTO.getTenantId())){
            throw new BizException(GlobalResponseResultEnum.PARAM_MISS_ERROR.getCode(),"tenantId不能为空");
        }
        Long tenantId = CsSecureUtil.isHeadquarters() ? userDTO.getTenantId() : CsSecureUtil.userTenantId();
        int count = userCount(tenantId);
        SysTenantEntity sysTenantEntity = sysTenantMapper.selectById(tenantId);
        return CsGenerateIdUtil.userAccount(sysTenantEntity.getAccountPrefix(),6,count+1);
    }

    @Override
    public boolean changeUserStatus(Long userId, Integer userStatus) {
        if(Objects.isNull(userId) || Objects.isNull(userStatus)) {
            throw new BizException(GlobalResponseResultEnum.PARAM_MISS_ERROR);
        }
        List<String> roleCodeList = sysRoleUserService.queryRoleCodeByUserId(userId);
        if(roleCodeList.contains(TenantConstant.SUPER_ADMINISTRATOR) || roleCodeList.contains(TenantConstant.TENANT_ADMINISTRATOR)) {
            throw new BizException(GlobalResponseResultEnum.OPERATING_SYS_DATA_CANNOT_ERROR);
        }
        UpdateWrapper<SysUserEntity> updateWrapper = Wrappers.update();
        SysUserEntity sysUserEntity = new SysUserEntity();
        sysUserEntity.setUserStatus(userStatus);
        updateWrapper.eq("user_id",userId);
        if(!CsSecureUtil.isHeadquarters()) {
            updateWrapper.eq("tenant_id", CsSecureUtil.userTenantId());
        }
        return this.update(sysUserEntity,updateWrapper);
    }

    @Override
    public boolean modifiedPersonalInfo(PersonalInfoDTO personalInfoDTO) {
        LambdaUpdateWrapper<SysUserEntity> updateWrapper = Wrappers.<SysUserEntity>lambdaUpdate()
                .set(SysUserEntity::getUserName,personalInfoDTO.getUserName())
                .set(SysUserEntity::getUserRealName,personalInfoDTO.getUserRealName())
                .set(SysUserEntity::getUserBirthday,personalInfoDTO.getUserBirthday())
                .set(SysUserEntity::getUserSex,personalInfoDTO.getUserSex())
                .set(SysUserEntity::getUserMobile,personalInfoDTO.getUserMobile())
                .eq(SysUserEntity::getUserId, CsSecureUtil.getUser().getUserId());
        return this.update(updateWrapper);
    }
}
