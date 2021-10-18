package com.cpms.system.modules.sys.dto;

import com.cpms.framework.common.core.base.BasePageDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description:
 * @author: gulang
 * @time: 2021/9/27 14:31
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QueryPostDTO extends BasePageDTO {
    private String postName;
    private Long tenantId;
}
