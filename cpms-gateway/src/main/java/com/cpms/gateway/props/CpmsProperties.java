package com.cpms.gateway.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: gulang
 * @time: 2021/12/20 13:57
 */
@Data
@Component
@ConfigurationProperties(prefix = "cpms")
public class CpmsProperties {
    private String jwtSecretKey;
}
