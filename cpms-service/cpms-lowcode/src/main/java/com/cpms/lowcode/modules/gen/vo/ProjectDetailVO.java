package com.cpms.lowcode.modules.gen.vo;

import com.cpms.framework.common.core.base.BaseVO;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @description:
 * @author: gulang
 * @time: 2022/1/25 11:11
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ProjectDetailVO extends BaseVO {
    @JsonSerialize(using = ToStringSerializer.class)
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
    private List<ProjectDbVO> dbDto;
}
