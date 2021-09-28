package com.cpms.system.modules.sys.vo;

import com.cpms.framework.common.core.base.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description:
 * @author: gulang
 * @time: 2021/9/27 14:50
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysPostVO extends BaseVO {
    private Long postId;
    private String postName;
    private Long deptId;
    private String deptName;
}
