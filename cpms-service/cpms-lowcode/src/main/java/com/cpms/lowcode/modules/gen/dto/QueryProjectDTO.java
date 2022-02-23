package com.cpms.lowcode.modules.gen.dto;

import com.cpms.framework.common.core.base.BasePageDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description:
 * @author: gulang
 * @time: 2022/1/26 10:35
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QueryProjectDTO extends BasePageDTO {
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
