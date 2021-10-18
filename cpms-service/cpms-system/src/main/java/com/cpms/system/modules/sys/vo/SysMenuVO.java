package com.cpms.system.modules.sys.vo;

import com.cpms.framework.common.core.base.BaseVO;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author gulang
 * @Description:
 * @time: 2021/8/7 20:47
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysMenuVO  extends BaseVO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long menuId;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentId;
    private List<SysMenuVO> children;
    private String name;
    private String code;
    private String alias;
    private String path;
    private String icon;
    private String component;
    private Integer sort;
    private Integer type;
    private Integer openFlag;

}
