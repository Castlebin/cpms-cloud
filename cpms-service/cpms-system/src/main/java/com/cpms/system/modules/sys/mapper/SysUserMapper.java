package com.cpms.system.modules.sys.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cpms.system.api.modules.sys.bo.SysUserLoginBO;
import com.cpms.system.api.modules.sys.dto.SysUserLginDTO;
import com.cpms.system.modules.sys.dto.ListUserDTO;
import com.cpms.system.modules.sys.entity.SysUserEntity;
import com.cpms.system.modules.sys.vo.SysUserVO;

import java.util.List;

/**
 * @description:
 * @author: gulang
 * @time: 2021/7/16 19:27
 */
@DS("master")
public interface SysUserMapper extends BaseMapper<SysUserEntity> {
    /**
     *  查询用户信息
     * @param sysUserLginDTO
     * @return
     */
    SysUserLoginBO querySysUserInfo(SysUserLginDTO sysUserLginDTO);

    int listUserCount(ListUserDTO listUserDTO);
    List<SysUserVO> listUser(ListUserDTO listUserDTO);
}
