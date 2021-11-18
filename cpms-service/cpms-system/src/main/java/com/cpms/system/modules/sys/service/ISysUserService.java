package com.cpms.system.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cpms.framework.common.core.base.BasePageVO;
import com.cpms.system.api.modules.sys.bo.SysUserLoginBO;
import com.cpms.system.api.modules.sys.dto.SysUserLginDTO;
import com.cpms.system.modules.sys.dto.PersonalInfoDTO;
import com.cpms.system.modules.sys.dto.QueryUserDTO;
import com.cpms.system.modules.sys.dto.ResetPasswordDTO;
import com.cpms.system.modules.sys.dto.SysUserDTO;
import com.cpms.system.modules.sys.entity.SysUserEntity;
import com.cpms.system.modules.sys.vo.SysUserDetailVO;
import com.cpms.system.modules.sys.vo.SysUserVO;

import java.util.List;

/**
 * @description:
 * @author: gulang
 * @time: 2021/7/16 19:30
 */
public interface ISysUserService extends IService<SysUserEntity> {
    /**
     * 用户列表
     * @param listUserDTO
     * @return
     */
    BasePageVO<SysUserVO> listUser(QueryUserDTO listUserDTO);

    /**
     * 获取用户详情
     * @param userDTO
     * @return
     */
    SysUserDetailVO getUserDetail(SysUserDTO userDTO);

    /**
     * 查看用户信息
     * @param userDTO
     * @return
     */
    SysUserDetailVO checkUserInfo(SysUserDTO userDTO);

    /**
     *  登录用户信息查询
     * @param sysUserLginDTO
     * @return
     */
    SysUserLoginBO querySysUserInfo(SysUserLginDTO sysUserLginDTO);

    /**
     * 删除用户
     * @param userDTO
     * @return
     */
    boolean deleteUser(SysUserDTO userDTO);

    /**
     * 新增用户
     * @param userDTO
     * @return
     */
    boolean addUser(SysUserDTO userDTO);

    /**
     * 更新用户
     * @param userDTO
     * @return
     */
    boolean updateUser(SysUserDTO userDTO);

    /**
     * 用户重置个人密码
     * @param resetPasswordDTO
     * @return
     */
    boolean resetPassword(ResetPasswordDTO resetPasswordDTO);

    /**
     * 管理员修改用户密码
     * @param resetPasswordDTO
     * @return
     */
    boolean modifiedPassword(ResetPasswordDTO resetPasswordDTO);

    int userCount(Long tenantId);

    String generateAccount(SysUserDTO userDTO);

    boolean changeUserStatus(Long userId,Integer userStatus);
    boolean modifiedPersonalInfo(PersonalInfoDTO personalInfoDTO);
}
