package com.cpms.system.modules.sys.dto;

import com.cpms.framework.common.core.base.BasePageDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description:
 * @author: gulang
 * @time: 2021/9/15 17:11
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QueryRoleDTO extends BasePageDTO {

    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色编码
     */
    private String roleCode;
    /**
     * 租户ID
     */
    private Long tenantId;

}
