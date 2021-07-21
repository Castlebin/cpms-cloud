package com.cpms.gateway.props;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 默认跳过的url配置
 * @author: gulang
 * @time: 2021/7/21 18:01
 */
public class DefaultUrlProperties {
    private static List<String> defaultSkipUrl = new ArrayList<>();

    static {
    }

    /**
     * 默认无需鉴权的API
     */
    public static List<String> getDefaultSkipUrl() {
        return defaultSkipUrl;
    }
}
