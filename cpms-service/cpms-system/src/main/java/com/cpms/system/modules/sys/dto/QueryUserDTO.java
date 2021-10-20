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
public class QueryUserDTO extends BasePageDTO {

    /**
     * 用户账号
     */
    private String userAccount;

    /**
     * 真实姓名
     */
    private String userRealName;

    /**
     * 租户ID
     */
    private Long tenantId;

    /**
     * 部门ID
     */
    private Long deptId;



}
