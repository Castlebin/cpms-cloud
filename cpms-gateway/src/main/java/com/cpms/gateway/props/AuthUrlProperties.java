package com.cpms.gateway.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: url配置
 * @author: gulang
 * @time: 2021/7/21 17:05
 */
@Data
@Component
@ConfigurationProperties("system.url")
public class AuthUrlProperties {
    /**
     * 放行API集合
     */
    private final List<String> skipUrl = new ArrayList<>();
}
