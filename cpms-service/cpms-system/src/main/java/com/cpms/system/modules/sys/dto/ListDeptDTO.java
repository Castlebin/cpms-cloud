package com.cpms.system.modules.sys.dto;

import com.cpms.framework.common.core.base.BasePageDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description:
 * @author: gulang
 * @time: 2021/9/23 10:55
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ListDeptDTO extends BasePageDTO {
    private String deptName;
    private Long tenantId;
    private String tenantName;
}
