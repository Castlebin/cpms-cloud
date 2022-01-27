package com.cpms.system.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cpms.system.modules.sys.dto.QueryTenantDTO;
import com.cpms.system.modules.sys.entity.SysTenantEntity;
import com.cpms.system.modules.sys.vo.SysTenantVO;

import java.util.List;

/**
 * @description:
 * @author: gulang
 * @time: 2021/9/22 11:26
 */
public interface SysTenantMapper extends BaseMapper<SysTenantEntity> {
    int listTenantCount(QueryTenantDTO listTenantDTO);

    List<SysTenantVO> listTenant(QueryTenantDTO listTenantDTO);
}
