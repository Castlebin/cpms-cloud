package com.cpms.lowcode.modules.gen.service;

import com.cpms.lowcode.modules.gen.dto.GenFuncDTO;
import com.cpms.lowcode.modules.gen.dto.SyncFieldDTO;

import javax.servlet.http.HttpServletResponse;

/**
 * @description:
 * @author: gulang
 * @time: 2022/1/27 10:16
 */
public interface IGenCodeService {
    void genScaffold(Long projectId, HttpServletResponse response);
    void  genFun(GenFuncDTO genFuncDTO, HttpServletResponse response);

    boolean syncField(SyncFieldDTO syncFieldDTO);
}
