package com.cpms.lowcode.modules.dynamic.dto;

import lombok.Data;

/**
 * @author gulang
 * @Description:
 * @time: 2022/2/18 15:11
 */
@Data
public class TableFieldDTO {
    private Long tableId;
    private String tableName;
    private String columnName;
    private String dataType;
    private String columnComment;
    private String columnKey;
    private String extra;
}
