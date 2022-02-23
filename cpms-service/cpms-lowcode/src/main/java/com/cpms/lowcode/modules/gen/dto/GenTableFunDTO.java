package com.cpms.lowcode.modules.gen.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author gulang
 * @Description:
 * @time: 2022/2/20 13:07
 */
@Data
@Builder
public class GenTableFunDTO {
    private String projectName;
    private String packageName;
    private String author;
    private String genDate;
    private String className;
    private String classAlias;
    private String moduleName;
    private String tableName;
    private List<TableColumnDTO> columnList;
}
