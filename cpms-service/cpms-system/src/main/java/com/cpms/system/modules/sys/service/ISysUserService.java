package com.cpms.system.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cpms.framework.common.core.base.BasePageVO;
import com.cpms.system.api.modules.sys.bo.SysUserLoginBO;
import com.cpms.system.api.modules.sys.dto.SysUserLginDTO;
import com.cpms.system.modules.sys.dto.QueryUserDTO;
import com.cpms.system.modules.sys.dto.ResetPasswordDTO;
import com.cpms.system.modules.sys.dto.SysUserDTO;
import com.cpms.system.modules.sys.entity.SysUserEntity;
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
     * 修改密码
     * @param resetPasswordDTO
     * @return
     */
    boolean resetPassword(ResetPasswordDTO resetPasswordDTO);

    int userCount(Long tenantId);

    String generateAccount();
}
