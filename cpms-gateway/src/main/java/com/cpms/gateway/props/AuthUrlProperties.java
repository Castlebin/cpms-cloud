package com.cpms.gateway.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 动态配置放行URL
 * @author: gulang
 * @time: 2021/7/21 17:05
 */
@Data
@Component
@ConfigurationProperties(prefix="system.url")
public class AuthUrlProperties {
    /**
     * 放行API集合
     */
    private final List<String> skipUrl = new ArrayList<>();
}
