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
@TableName("cpms_code_project")
public class ProjectEntity extends BaseEntity {
    @TableId(value = "project_id",type = IdType.ASSIGN_ID)
    private Long projectId;
    private String projectName;
    private String packageName;
    private Integer projectPort;
    private String fileEncoding;
    private String jdkVersion;
    private String contextPath;
    private String groupId;
    private String artifactId;
    private String version;
    private String projectDesc;
    private String teamName;
    private String leader;

}
