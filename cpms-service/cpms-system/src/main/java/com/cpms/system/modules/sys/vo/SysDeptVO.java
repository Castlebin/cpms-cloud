package com.cpms.system.modules.sys.vo;

import com.cpms.framework.common.core.base.BaseVO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @description:
 * @author: gulang
 * @time: 2021/9/23 11:11
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysDeptVO extends BaseVO {
    private Long deptId;
    private String deptName;
    private Integer parentId;
    private String tenantName;
    private Long tenantId;
    private String superiorDept;
    private List<SysMenuVO> children;
}