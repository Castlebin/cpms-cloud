package com.cpms.system.modules.sys.controller;

import com.cpms.framework.common.core.api.Result;
import com.cpms.framework.common.core.api.ResultUtil;
import com.cpms.framework.common.core.base.BasePageVO;
import com.cpms.framework.common.core.node.BaseTreeNode;
import com.cpms.framework.log.annotations.OperLog;
import com.cpms.framework.mybatis.groups.AddGroup;
import com.cpms.framework.mybatis.groups.DeleteGroup;
import com.cpms.framework.mybatis.groups.UpdateGroup;
import com.cpms.framework.secure.annotations.PreAuth;
import com.cpms.system.modules.sys.dto.SysDeptDTO;
import com.cpms.system.modules.sys.dto.QueryDeptDTO;
import com.cpms.system.modules.sys.service.ISysDeptService;
import com.cpms.system.modules.sys.vo.SysDeptVO;
import com.cpms.system.modules.sys.vo.DeptTreeVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author: gulang
 * @time: 2021/9/23 10:47
 */
@RestController
@RequestMapping("/sys-dept")
public class SysDeptController {
    @Resource
    private ISysDeptService sysDeptService;
    /**
     *  部门列表
     * @param listDeptDTO
     * @return
     */
    @PostMapping("/list")
    @PreAuth("sys_dept_view")
    public Result<BasePageVO<SysDeptVO>> listDept(@RequestBody QueryDeptDTO listDeptDTO){
        return ResultUtil.success(sysDeptService.listDept(listDeptDTO));
    }

    /**
     *  所有租户部门树形结构
     * @return
     */
    @PostMapping("/allDeptTree")
    public Result<List<DeptTreeVO>> allDeptTree(@RequestBody QueryDeptDTO queryDeptDTO){
        return ResultUtil.success(sysDeptService.allDeptTree(queryDeptDTO));
    }

    /**
     *  租户部门树形结构
     * @return
     */
    @GetMapping("/tenantDeptTree")
    public Result<List<DeptTreeVO>> tenantDeptTree(){
        return ResultUtil.success(sysDeptService.tenantDeptTree());
    }


    /**
     *  添加操作
     * @param deptDTO
     * @return
     */
    @PostMapping("/add")
    @PreAuth("sys_dept_add")
    @OperLog(desc = "新增部门")
    public Result<Void> addDept(@Validated(AddGroup.class)@RequestBody SysDeptDTO deptDTO){
        return ResultUtil.status(sysDeptService.addDept(deptDTO));
    }

    /**
     *  修改操作
     * @param deptDTO
     * @return
     */
    @PostMapping("/update")
    @PreAuth("sys_dept_edit")
    @OperLog(desc = "修改部门")
    public Result<Void> updateDept(@Validated(UpdateGroup.class)@RequestBody SysDeptDTO deptDTO){
        return ResultUtil.status(sysDeptService.updateDept(deptDTO));
    }

    /**
     *  删除操作
     * @param deptDTO
     * @return
     */
    @PostMapping("/delete")
    @PreAuth("sys_dept_delete")
    @OperLog(desc = "删除部门")
    public Result<Void> deleteDept(@Validated(DeleteGroup.class)@RequestBody SysDeptDTO deptDTO){
        return ResultUtil.status(sysDeptService.deleteDept(deptDTO));
    }
}
