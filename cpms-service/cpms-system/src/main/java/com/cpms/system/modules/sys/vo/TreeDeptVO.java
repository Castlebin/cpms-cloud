package com.cpms.system.modules.sys.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.List;

/**
 * @author gulang
 * @Description:
 * @time: 2021/10/10 18:43
 */
@Data
public class TreeDeptVO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long deptId;
    private String deptName;
    private Integer parentId;
    private List<TreeDeptVO> children;
}
