package com.cpms.system.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cpms.framework.common.core.base.BasePageVO;
import com.cpms.framework.common.exception.BizException;
import com.cpms.framework.common.utils.CsSecureUtil;
import com.cpms.system.api.modules.sys.bo.SysUserLoginBO;
import com.cpms.system.api.modules.sys.dto.SysUserLginDTO;
import com.cpms.system.common.constants.SystemConstant;
import com.cpms.system.common.enums.SystemResponseResultEnum;
import com.cpms.system.modules.sys.dto.ListUserDTO;
import com.cpms.system.modules.sys.dto.ResetPasswordDTO;
import com.cpms.system.modules.sys.dto.UserDTO;
import com.cpms.system.modules.sys.entity.SysRoleUserEntity;
import com.cpms.system.modules.sys.entity.SysUserEntity;
import com.cpms.system.modules.sys.mapper.SysUserMapper;
import com.cpms.system.modules.sys.service.ISysUserService;
import com.cpms.system.modules.sys.service.SysRoleUserService;
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
    private SysRoleUserService sysRoleUserService;

    @Override
    public BasePageVO<SysUserVO> listUser(ListUserDTO listUserDTO) {
        BasePageVO<SysUserVO> listUserVO = new BasePageVO();
        List<SysUserVO> sysUserVoList;
        listUserDTO.setTenantId(CsSecureUtil.getUser().getTenantId());
        int count = sysUserMapper.listUserCount(listUserDTO);
        if(count == 0 ){
            sysUserVoList = Lists.newArrayList();
        }else {
            sysUserVoList = sysUserMapper.listUser(listUserDTO);
        }
        listUserVO.setTotal(count);
        listUserVO.setList(sysUserVoList);
        return listUserVO;
    }

    @Override
    public SysUserLoginBO querySysUserInfo(SysUserLginDTO sysUserLginDTO) {
        return sysUserMapper.querySysUserInfo(sysUserLginDTO);
    }

    @Override
    public boolean deleteUser(UserDTO userDTO) {
        LambdaUpdateWrapper<SysUserEntity> updateWrapper = Wrappers.<SysUserEntity>lambdaUpdate()
                .set(SysUserEntity::getDelFlag, SystemConstant.DEL_FLAG_DELETED)
                .eq(SysUserEntity::getUserId, userDTO.getUserId())
                .eq(SysUserEntity::getTenantId, CsSecureUtil.getUser().getTenantId());
        return this.update(updateWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addUser(UserDTO userDTO) {
        Integer count = sysUserMapper.selectCount(Wrappers.<SysUserEntity>query().lambda().eq(SysUserEntity::getUserAccount, userDTO.getUserAccount()));
        if( count > 0){
            throw new BizException(SystemResponseResultEnum.USER_ALREADY_EXISTS_ERROR);
        }
        SysUserEntity sysUserEntity = new SysUserEntity();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        userDTO.setUserPassword(bCryptPasswordEncoder.encode(userDTO.getUserPassword()));
        BeanUtils.copyProperties(userDTO,sysUserEntity);
        this.save(sysUserEntity);
        String[] roleIdArr = userDTO.getRoleIds().split(",");
        List<Long> roleIdList = Arrays.stream(roleIdArr).mapToLong(Long::parseLong).boxed().collect(Collectors.toList());
        List<SysRoleUserEntity> roleUserEntityList = roleIdList.stream().map(e -> {
            SysRoleUserEntity sysRoleUserEntity = new SysRoleUserEntity();
            sysRoleUserEntity.setUserId(sysUserEntity.getUserId());
            sysRoleUserEntity.setRoleId(e);
            return sysRoleUserEntity;
        }).collect(Collectors.toList());
        sysRoleUserService.saveBatch(roleUserEntityList);
        return true ;
    }

    @Override
    public boolean updateUser(UserDTO userDTO) {
        SysUserEntity sysUserEntity = new SysUserEntity();
        sysUserEntity.setDeptId(userDTO.getDeptId());
        sysUserEntity.setUserAvatar(userDTO.getUserAvatar());
        sysUserEntity.setUserName(userDTO.getUserName());
        sysUserEntity.setUserSex(userDTO.getUserSex());
        sysUserEntity.setUserMobile(userDTO.getUserMobile());
        UpdateWrapper<SysUserEntity> updateWrapper = Wrappers.update();
        updateWrapper.eq("user_id",userDTO.getUserId());
        updateWrapper.eq("tenant_id", CsSecureUtil.getUser().getTenantId());
        return this.update(sysUserEntity,updateWrapper);
    }


    @Override
    public boolean resetPassword(ResetPasswordDTO resetPasswordDTO) {
        if(!Objects.equals(resetPasswordDTO.getUserId(),CsSecureUtil.getUser().getUserId())) {
            throw new BizException(SystemResponseResultEnum.FORBIDDEN_CHANGE_OTHER_PEOPLE_PASSWORD_ERROR);
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        SysUserEntity sysUserEntity = sysUserMapper.selectOne(Wrappers.<SysUserEntity>query().lambda().eq(SysUserEntity::getUserId, resetPasswordDTO.getUserId()));
        if(!bCryptPasswordEncoder.matches(resetPasswordDTO.getOldPassword(),sysUserEntity.getUserPassword())) {
            throw new BizException(SystemResponseResultEnum.ORIGINAL_PASSWORD_NOT_MATCH_ERROR);
        }
        LambdaUpdateWrapper<SysUserEntity> updateWrapper = Wrappers.<SysUserEntity>lambdaUpdate()
                .set(SysUserEntity::getUserPassword, bCryptPasswordEncoder.encode(resetPasswordDTO.getNewPassword()))
                .eq(SysUserEntity::getUserId, resetPasswordDTO.getUserId())
                .eq(SysUserEntity::getTenantId, CsSecureUtil.getUser().getTenantId());
        return this.update(updateWrapper);
    }



}
