package com.cpms.system.modules.sys.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cpms.system.modules.sys.dto.ListTenantDTO;
import com.cpms.system.modules.sys.entity.SysTenantEntity;
import com.cpms.system.modules.sys.vo.SysTenantVO;

import java.util.List;

/**
 * @description:
 * @author: gulang
 * @time: 2021/9/22 11:26
 */
@DS("master")
public interface SysTenantMapper extends BaseMapper<SysTenantEntity> {
    int listTenantCount(ListTenantDTO listTenantDTO);

    List<SysTenantVO> listTenant(ListTenantDTO listTenantDTO);
}