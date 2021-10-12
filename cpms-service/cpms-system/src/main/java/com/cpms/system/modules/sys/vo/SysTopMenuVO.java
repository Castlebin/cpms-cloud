package com.cpms.system.modules.sys.vo;

import com.cpms.framework.common.core.base.BaseVO;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description:
 * @author: gulang
 * @time: 2021/8/30 14:29
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysTopMenuVO extends BaseVO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long topMenuId;
    private String topMenuName;
    private Integer sort;
    private String path;
    private String relationMenuIds;
    private Integer type;
    private String icon;
}
