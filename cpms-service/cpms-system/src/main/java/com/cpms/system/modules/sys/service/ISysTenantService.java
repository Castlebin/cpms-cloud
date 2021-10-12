package com.cpms.system.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cpms.framework.common.core.base.BasePageVO;
import com.cpms.system.modules.sys.dto.ListTenantDTO;
import com.cpms.system.modules.sys.dto.SysTenantDTO;
import com.cpms.system.modules.sys.entity.SysTenantEntity;
import com.cpms.system.modules.sys.vo.InitTenantAccountVO;
import com.cpms.system.modules.sys.vo.SysTenantVO;

import java.util.List;


/**
 * @description:
 * @author: gulang
 * @time: 2021/9/22 11:27
 */
public interface ISysTenantService extends IService<SysTenantEntity> {
    InitTenantAccountVO addTenant(SysTenantDTO tenantDTO);
    boolean updateTenant(SysTenantDTO tenantDTO);
    boolean deleteTenant(SysTenantDTO tenantDTO);
    BasePageVO<SysTenantVO> listTenant(ListTenantDTO listTenantDTO);
    List<SysTenantVO> tenants();
}
