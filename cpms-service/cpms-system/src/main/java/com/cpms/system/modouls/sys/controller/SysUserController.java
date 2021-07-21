package com.cpms.system.modouls.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cpms.common.core.api.Result;
import com.cpms.common.core.api.ResultUtil;
import com.cpms.system.common.constants.SystemConstant;
import com.cpms.system.modouls.sys.dto.UserDTO;
import com.cpms.system.modouls.sys.entity.SysUserEntity;
import com.cpms.system.modouls.sys.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: gulang
 * @time: 2021/7/21 14:22
 */
@RestController
@RequestMapping("/sysUser")
public class SysUserController {
    @Autowired
    private ISysUserService sysUserService;

    @PostMapping("/deleteUser")
    public Result<Void> deleteUser(@RequestBody UserDTO userDTO){
        SysUserEntity sysUserEntity = new SysUserEntity();
        sysUserEntity.setDelFlag(SystemConstant.DEL_FLAG_DELETED);
        QueryWrapper<SysUserEntity> query = Wrappers.query();
        query.eq("user_id",userDTO.getUserId());
        query.eq("tenant_id",1);
        return ResultUtil.status(sysUserService.update(sysUserEntity,query));
    }
}
