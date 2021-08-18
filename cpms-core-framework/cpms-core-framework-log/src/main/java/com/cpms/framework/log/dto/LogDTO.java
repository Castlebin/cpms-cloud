package com.cpms.framework.log.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @description: 操作日志实体类
 * @author: gulang
 * @time: 2021/8/18 17:14
 */
@Data
@ToString
public class LogDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String title;
    private String serviceName;
    private String handleIp;
    private String reqUrl;
    private String reqMethod;
    private String reqParams;
    private String createBy;
    private Long   exeTime;
    private String resultMsg;
}
