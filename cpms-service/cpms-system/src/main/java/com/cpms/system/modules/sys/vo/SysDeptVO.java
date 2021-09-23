package com.cpms.system.modules.sys.vo;

import com.cpms.framework.common.core.base.BaseVO;
import lombok.Data;

/**
 * @description:
 * @author: gulang
 * @time: 2021/9/23 11:11
 */
@Data
public class SysDeptVO extends BaseVO {
    private Long deptId;
    private String deptName;
    private Integer parentId;
}
