package com.cpms.log.modules.log.dto;

import com.cpms.framework.common.core.base.BasePageDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description:
 * @author: gulang
 * @time: 2021/9/23 16:16
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ListLogDTO extends BasePageDTO {
    private String title;
    private String serviceName;
    private String handleIp;
    private String reqUrl;
    private String createBy;
}
