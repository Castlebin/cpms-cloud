package com.cpms.lowcode.modules.gen.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @description:
 * @author: gulang
 * @time: 2022/2/15 17:06
 */
@Data
@Builder
public class DbParamDTO {
    private String dbUrl;
    private String dbName;
    private String userName;
    private String password;
    /**
     * 是否为项目主库：0-不是，1-是
     */
    private Integer dbPrimary;
}
