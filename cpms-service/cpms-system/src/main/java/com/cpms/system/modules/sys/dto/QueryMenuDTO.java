package com.cpms.system.modules.sys.dto;

import com.cpms.framework.common.core.base.BasePageDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author gulang
 * @Description:
 * @time: 2021/9/29 9:51
 */
@Data
public class QueryMenuDTO {
    private String name;
    private String code;
    private String alias;
    private String type;
}
