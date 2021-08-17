package com.cpms.system.modules.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cpms.common.core.api.Result;
import com.cpms.common.core.api.ResultUtil;
import com.cpms.framework.log.annotations.OperLog;
import com.cpms.framework.security.annotations.PreAuth;
import com.cpms.system.common.constants.SystemConstant;
import com.cpms.system.modules.sys.dto.UserDTO;
import com.cpms.system.modules.sys.entity.SysUserEntity;
import com.cpms.system.modules.sys.service.ISysUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description:
 * @author: gulang
 * @time: 2021/7/21 14:22
 */
@RestController
@RequestMapping("/sys-user")
public class SysUserController {
    @Resource
    private ISysUserService sysUserService;

    @PostMapping("/delete")
    @PreAuth("sys_user_delete")
    @OperLog(desc = "删除用户")
    public Result<Void> deleteUser(@RequestBody UserDTO userDTO){
        SysUserEntity sysUserEntity = new SysUserEntity();
        sysUserEntity.setDelFlag(SystemConstant.DEL_FLAG_DELETED);
        QueryWrapper<SysUserEntity> query = Wrappers.query();
        query.eq("user_id",userDTO.getUserId());
        query.eq("tenant_id",1);
        return ResultUtil.status(sysUserService.update(sysUserEntity,query));
    }
}
