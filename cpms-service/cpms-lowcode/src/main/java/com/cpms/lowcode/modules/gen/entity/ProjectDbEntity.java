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
@TableName("cpms_code_project_db")
public class ProjectDbEntity extends BaseEntity {
    @TableId(value = "db_id",type = IdType.ASSIGN_ID)
    private Long dbId;
    private Long projectId;
    private String dbDriverClass;
    private String dbIp;
    private String dbPort;
    private String dbName;
    private String dbUser;
    private String dbPassword;
    private Integer dbPrimary;

}
