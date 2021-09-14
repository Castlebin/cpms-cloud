package com.cpms.framework.redis.redisson;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;


/**
 * @description: redisson 配置信息
 * @author: gulang
 * @time: 2021/8/24 17:20
 */
@Data
@ConfigurationProperties(prefix = "spring.redis.server")
public class RedissonProperty {
    private String serverMode;
    private String password;
    private String clientName;
    private Single single;
    private Cluster cluster;
    private Sentinel sentinel;

    @Getter
    @Setter
    public static class  Single{
        private String address;
    }

    @Getter
    @Setter
    public static class  Cluster{
        private List<String> nodeHosts;
    }

    @Getter
    @Setter
    public static class  Sentinel{
        private String masterName;
        private List<String> nodeHosts;
    }

}
