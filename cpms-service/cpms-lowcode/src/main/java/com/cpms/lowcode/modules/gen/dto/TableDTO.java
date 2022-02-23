package com.cpms.lowcode.modules.gen.dto;

import lombok.Data;


/**
 * @author gulang
 * @Description:
 * @time: 2022/1/22 19:57
 */
@Data
public class TableDTO {
    private Long tableId;
    private Long dbId;
    private String tableName;
    private String tableComment;
    private String className;
    private String moduleName;

}
