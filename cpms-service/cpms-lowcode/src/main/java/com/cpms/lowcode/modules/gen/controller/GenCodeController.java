package com.cpms.lowcode.modules.gen.controller;

import com.cpms.framework.common.core.api.Result;
import com.cpms.framework.common.core.api.ResultUtil;
import com.cpms.framework.mybatis.groups.ValidatorGroup;
import com.cpms.lowcode.modules.gen.dto.GenFuncDTO;
import com.cpms.lowcode.modules.gen.dto.SyncFieldDTO;
import com.cpms.lowcode.modules.gen.service.IGenCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;



/**
 * @author gulang
 * @Description:
 * @time: 2022/1/17 20:08
 */
@RestController
@RequestMapping("/gen-code")
@Slf4j
public class GenCodeController {
    @Resource
    private IGenCodeService genCodeService;
    /**
     * 生成工程脚手架
     */
    @GetMapping("/genScaffold")
    public void  genScaffold(@RequestParam(name = "projectId") Long projectId, HttpServletResponse response){
        genCodeService.genScaffold(projectId,response);
    }

    /**
     * 生成单表功能
     */
    @PostMapping("/genFun")
    public void  genFun(@Validated(ValidatorGroup.Other.class)@RequestBody GenFuncDTO genFuncDTO, HttpServletResponse response){
        genCodeService.genFun(genFuncDTO,response);
    }

    @PostMapping("/syncField")
    public Result<Void> syncField(@Validated(ValidatorGroup.Other.class)@RequestBody SyncFieldDTO syncFieldDTO){
        return  ResultUtil.status(genCodeService.syncField(syncFieldDTO));
    }

}
