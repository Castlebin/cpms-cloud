package com.cpms.system.modules.sys.service;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cpms.framework.common.core.base.BasePageVO;
import com.cpms.system.modules.sys.dto.QueryDeptDTO;
import com.cpms.system.modules.sys.dto.SysDeptDTO;
import com.cpms.system.modules.sys.entity.SysDeptEntity;
import com.cpms.system.modules.sys.vo.SysDeptVO;

import java.util.List;

/**
 * @description:
 * @author: gulang
 * @time: 2021/7/20 17:52
 */
public interface ISysDeptService extends IService<SysDeptEntity> {
    BasePageVO<SysDeptVO> listDept(QueryDeptDTO listDeptDTO);
    boolean addDept(SysDeptDTO deptDTO);
    boolean updateDept(SysDeptDTO deptDTO);
    boolean deleteDept(SysDeptDTO deptDTO);
    List<Tree<String>> treeDept();
}
