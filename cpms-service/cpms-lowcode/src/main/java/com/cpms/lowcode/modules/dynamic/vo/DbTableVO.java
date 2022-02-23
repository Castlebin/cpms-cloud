package com.cpms.lowcode.modules.dynamic.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * @author gulang
 * @Description:
 * @time: 2022/2/19 17:14
 */
@Data
public class DbTableVO {
    private String tableName;
    /**
     * 0-未同步 1-已同步
     */
    private Integer syncStatus;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long tableId;
    private String tableComment;
}
