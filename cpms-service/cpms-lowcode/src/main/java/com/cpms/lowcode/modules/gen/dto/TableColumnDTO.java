package com.cpms.lowcode.modules.gen.dto;

import lombok.Data;


/**
 * @author gulang
 * @Description:
 * @time: 2022/1/22 19:57
 */
@Data
public class TableColumnDTO {
    private Long columnId;
    private Long tableId;
    private String columnName;
    private String columnType;
    private String columnComment;
    private String javaField;
    private String javaType;
    private Integer ifPk;
    private Integer ifIncrement;
    private Integer sort;

}
