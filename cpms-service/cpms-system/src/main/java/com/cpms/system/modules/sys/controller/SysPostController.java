package com.cpms.system.modules.sys.controller;

import com.cpms.framework.common.core.api.Result;
import com.cpms.framework.common.core.api.ResultUtil;
import com.cpms.framework.common.core.base.BasePageVO;
import com.cpms.framework.mybatis.groups.AddGroup;
import com.cpms.framework.mybatis.groups.DeleteGroup;
import com.cpms.framework.mybatis.groups.UpdateGroup;
import com.cpms.framework.secure.annotations.PreAuth;
import com.cpms.system.modules.sys.dto.ListPostDTO;
import com.cpms.system.modules.sys.dto.SysPostDTO;
import com.cpms.system.modules.sys.service.ISysPostService;
import com.cpms.system.modules.sys.vo.SysPostVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description:
 * @author: gulang
 * @time: 2021/9/23 10:47
 */
@RestController
@RequestMapping("/sys-post")
public class SysPostController {
    @Resource
    private ISysPostService sysPostService;
    /**
     *  岗位列表
     * @param listPostDTO
     * @return
     */
    @PostMapping("/list")
    public Result<BasePageVO<SysPostVO>> listPost(@RequestBody ListPostDTO listPostDTO){
        return ResultUtil.success(sysPostService.listPost(listPostDTO));
    }


    /**
     *  添加操作
     * @param postDTO
     * @return
     */
    @PostMapping("/add")
    @PreAuth("sys_post_add")
    public Result<Void> addPost(@Validated(AddGroup.class)@RequestBody SysPostDTO postDTO){
        return ResultUtil.status(sysPostService.addPost(postDTO));
    }

    /**
     *  修改操作
     * @param postDTO
     * @return
     */
    @PostMapping("/update")
    @PreAuth("sys_post_update")
    public Result<Void> updatePost(@Validated(UpdateGroup.class)@RequestBody SysPostDTO postDTO){
        return ResultUtil.status(sysPostService.updatePost(postDTO));
    }

    /**
     *  删除操作
     * @param postDTO
     * @return
     */
    @PostMapping("/delete")
    @PreAuth("sys_post_delete")
    public Result<Void> deletePost(@Validated(DeleteGroup.class)@RequestBody SysPostDTO postDTO){
        return ResultUtil.status(sysPostService.deletePost(postDTO));
    }
}
