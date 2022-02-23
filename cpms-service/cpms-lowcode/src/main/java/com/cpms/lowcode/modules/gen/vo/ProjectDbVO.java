package com.cpms.lowcode.modules.gen.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * @author gulang
 * @Description:
 * @time: 2022/2/1 12:27
 */
@Data
public class ProjectDbVO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long dbId;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long projectId;
    private String dbDriverClass;
    private String dbIp;
    private String dbPort;
    private String dbName;
    private String dbUser;
    private String dbPassword;
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer dbPrimary;
}
