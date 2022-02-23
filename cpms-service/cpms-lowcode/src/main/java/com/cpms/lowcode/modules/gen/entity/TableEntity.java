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
@TableName("cpms_code_table")
public class TableEntity extends BaseEntity {
    @TableId(value = "table_id",type = IdType.ASSIGN_ID)
    private Long tableId;
    private Long dbId;
    private String tableName;
    private String tableComment;
    private String className;
    private String moduleName;

}
