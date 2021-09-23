package com.cpms.system.modules.sys.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cpms.system.modules.sys.dto.ListDeptDTO;
import com.cpms.system.modules.sys.entity.SysDeptEntity;
import com.cpms.system.modules.sys.vo.SysDeptVO;

import java.util.List;

/**
 * @description:
 * @author: gulang
 * @time: 2021/7/20 17:51
 */
@DS("master")
public interface SysDeptMapper extends BaseMapper<SysDeptEntity> {
    int listDeptCount(ListDeptDTO listDeptDTO);

    List<SysDeptVO> listDept(ListDeptDTO listDeptDTO);
}
