package com.cpms.lowcode.modules.gen.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cpms.framework.mybatis.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author gulang
 * @Description:
 * @time: 2022/1/22 19:57
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("cpms_code_table_column")
public class TableColumnEntity extends BaseEntity {
    @TableId(value = "column_id",type = IdType.ASSIGN_ID)
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
