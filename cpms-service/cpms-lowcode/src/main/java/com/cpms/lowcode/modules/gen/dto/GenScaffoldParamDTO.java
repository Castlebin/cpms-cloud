package com.cpms.lowcode.modules.gen.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: gulang
 * @time: 2022/2/15 17:01
 */
@Data
@Builder
public class GenScaffoldParamDTO {
    private String projectName;
    private String packageName;
    private String fileEncoding;
    private String jdkVersion;
    private Integer projectPort;
    private String startupName;
    private String contextPath;
    private String groupId;
    private String artifactId;
    private String version;
    private String author;
    private String genDate;
    private String projectDesc;
    private List<DbParamDTO> dbList;
}
