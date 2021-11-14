package com.cpms.system.modules.sys.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cpms.system.modules.sys.dto.QueryDeptDTO;
import com.cpms.system.modules.sys.entity.SysDeptEntity;
import com.cpms.system.modules.sys.vo.SysDeptVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description:
 * @author: gulang
 * @time: 2021/7/20 17:51
 */
@DS("master")
public interface SysDeptMapper extends BaseMapper<SysDeptEntity> {
    int listDeptCount(QueryDeptDTO listDeptDTO);

    List<SysDeptVO> listDept(QueryDeptDTO listDeptDTO);

    List<SysDeptEntity> findTenantDeptNodes(@Param("deptId") Long deptId,@Param("tenantId") Long tenantId);
}
