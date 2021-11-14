package com.cpms.system.modules.sys.controller;

import com.cpms.framework.common.core.api.Result;
import com.cpms.framework.common.core.api.ResultUtil;
import com.cpms.framework.common.core.base.BasePageVO;
import com.cpms.framework.log.annotations.OperLog;
import com.cpms.framework.mybatis.groups.AddGroup;
import com.cpms.framework.mybatis.groups.DeleteGroup;
import com.cpms.framework.mybatis.groups.SelectGroup;
import com.cpms.framework.mybatis.groups.UpdateGroup;
import com.cpms.framework.secure.annotations.PreAuth;
import com.cpms.system.modules.sys.dto.QueryUserDTO;
import com.cpms.system.modules.sys.dto.ResetPasswordDTO;
import com.cpms.system.modules.sys.dto.SysUserDTO;
import com.cpms.system.modules.sys.service.ISysUserService;
import com.cpms.system.modules.sys.vo.SysUserDetailVO;
import com.cpms.system.modules.sys.vo.SysUserVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 用户列表
     * @param listUserDTO
     * @return
     */
    @PostMapping("/list")
    @PreAuth("sys_user_view")
    public Result<BasePageVO<SysUserVO>> listUser(@RequestBody QueryUserDTO listUserDTO){
        return ResultUtil.success(sysUserService.listUser(listUserDTO));
    }

    /**
     * 获取用户详情
     * @param userDTO
     * @return
     */
    @PostMapping("/getUserDetail")
    @PreAuth("sys_user_edit")
    public Result<SysUserDetailVO> getUserDetail(@Validated(SelectGroup.class)@RequestBody SysUserDTO userDTO){
        return ResultUtil.success(sysUserService.getUserDetail(userDTO));
    }

    /**
     * 查看用户信息
     * @param userDTO
     * @return
     */
    @PostMapping("/checkUserInfo")
    @PreAuth("sys_user_view")
    public Result<SysUserDetailVO> checkUserInfo(@Validated(SelectGroup.class)@RequestBody SysUserDTO userDTO){
        return ResultUtil.success(sysUserService.checkUserInfo(userDTO));
    }

    @PostMapping("/delete")
    @PreAuth("sys_user_delete")
    @OperLog(desc = "删除用户")
    public Result<Void> deleteUser(@Validated(DeleteGroup.class)@RequestBody SysUserDTO userDTO){
        return ResultUtil.status(sysUserService.deleteUser(userDTO));
    }

    @PostMapping("/add")
    @PreAuth("sys_user_add")
    @OperLog(desc = "添加用户")
    public Result<Void> addUser(@Validated(AddGroup.class) @RequestBody SysUserDTO userDTO){
        return ResultUtil.status(sysUserService.addUser(userDTO));
    }

    @PostMapping("/update")
    @PreAuth("sys_user_edit")
    @OperLog(desc = "修改用户")
    public Result<Void> updateUser(@Validated(UpdateGroup.class) @RequestBody SysUserDTO userDTO){
        return ResultUtil.status(sysUserService.updateUser(userDTO));
    }

    @GetMapping("/changeUserStatus")
    @PreAuth("sys_user_edit")
    @OperLog(desc = "修改用户状态")
    public Result<Void> changeStatus( @RequestParam Long userId,  @RequestParam Integer userStatus){
        return ResultUtil.status(sysUserService.changeUserStatus(userId,userStatus));
    }

    /**
     * 重置个人密码
     * @param resetPasswordDTO
     * @return
     */
    @PostMapping("/resetPassword")
    public Result<Void> resetPassword(@Validated(UpdateGroup.class) @RequestBody ResetPasswordDTO resetPasswordDTO){
        return ResultUtil.status(sysUserService.resetPassword(resetPasswordDTO));
    }

    /**
     * 修改用户密码
     * @param resetPasswordDTO
     * @return
     */
    @PostMapping("/modifiedPassword")
    @PreAuth("sys_user_modified_pwd")
    @OperLog(desc = "修改用户密码")
    public Result<Void> modifiedPassword(@RequestBody ResetPasswordDTO resetPasswordDTO){
        return ResultUtil.status(sysUserService.modifiedPassword(resetPasswordDTO));
    }

    @PostMapping("/generateAccount")
    public Result<String> generateAccount(@RequestBody SysUserDTO userDTO){
        return ResultUtil.success(sysUserService.generateAccount(userDTO));
    }


}
