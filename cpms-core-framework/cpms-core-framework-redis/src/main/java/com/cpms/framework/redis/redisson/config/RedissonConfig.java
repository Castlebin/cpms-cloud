package com.cpms.framework.redis.redisson.config;

import com.cpms.framework.redis.redisson.RedissonProperty;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;
import org.redisson.config.SentinelServersConfig;
import org.redisson.config.SingleServerConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: redisson 配置
 * @author: gulang
 * @time: 2021/8/24 16:36
 */

/**
 * @EnableConfigurationProperties 将使用了@ConfigurationProperties注解的类（该类没有@Component注解）注入到spring上下文中，
 * 但是 使用了@EnableConfigurationProperties注解的类需要有能被扫描的注解
 */
@EnableConfigurationProperties(RedissonProperty.class)
@Configuration
@Slf4j
public class RedissonConfig {
    /**
     * 连接服务模式： single(单机模式)、cluster（集群模式）、sentinel（哨兵模式）
     */
    private static final List<String> CONNECT_SERVER_MODE= Lists.newArrayList("single", "cluster", "sentinel");
    /**
     * 数据库编号
     */
    private static final int  DATABASE = 0;
    /**
     * 如果当前连接池里的连接数量超过了最小空闲连接数，而同时有连接空闲时间超过了该数值
     * 那么这些连接将会自动被关闭，并从连接池里去掉。时间单位是毫秒。默认10000
     */
    private static final int IDLE_CONNECTION_TIMEOUT = 1000;
    /**
     * 同节点建立连接时的等待超时。时间单位是毫秒。默认10000
     */
    private static final int CONNECT_TIMEOUT = 5000;
    /**
     * 等待节点回复命令的时间。该时间从命令发送成功时开始计时。默认3000
     */
    private static final int TIMEOUT = 3000;
    /**
     * 命令失败重试次数,如果尝试达到 retryAttempts（命令失败重试次数） 仍然不能将命令发送至某个指定的节点时，将抛出错误。
     * 如果尝试在此限制之内发送成功，则开始启用 timeout（命令等待超时） 计时
     */
    private static final int RETRY_ATTEMPTS = 3;
    /**
     * 多从节点的环境里，每个主节点的最小保持连接数（长连接）。长期保持一定数量的连接有利于提高瞬时写入反应速度。默认32
     */
    private static final int MASTER_CONNECTION_MINIMUM_IDLE_SIZE = 1;
    /**
     * 多从节点的环境里，每个从服务节点里用于普通操作（非 发布和订阅）的最小保持连接数（长连接），
     * 长期保持一定数量的连接有利于提高瞬时读取反映速度。默认32
     */
    private static final int SLAVE_CONNECTION_MINIMUM_IDLE_SIZE = 1;

    @Resource
    private RedissonProperty redissonProperty;

    RedissonConfig(){}

    @PostConstruct
    public void init(){
        String serversMode = redissonProperty.getServerMode();
        if(StringUtils.isNotBlank(serversMode) && !CONNECT_SERVER_MODE.contains(serversMode)) {
            throw new IllegalArgumentException("current servers-mode: ["+serversMode+"] not support !!!");
        }
    }

    @Bean
    /**
     * 条件注解，当name指定的属性值与havingValue相等时，则会对指定对象进行IOC注入，反之则不会，如果matchIfMissing为true，不相等也会
     */
    @ConditionalOnProperty(name = "spring.redisson.server-mode", havingValue = "single")
    public RedissonClient singleRedisson() {
        log.info("======================RedissonClient连接模式[single]=========================");
        Config config = new Config();
        SingleServerConfig singleServerConfig = config.useSingleServer();
        String address = redissonProperty.getSingle().getAddress();
        if(StringUtils.isBlank(address)) {
            throw new IllegalArgumentException("please config single node address !!! ");
        }
        singleServerConfig.setAddress("redis://"+address);
        singleServerConfig.setIdleConnectionTimeout(IDLE_CONNECTION_TIMEOUT);
        singleServerConfig.setConnectTimeout(CONNECT_TIMEOUT);
        singleServerConfig.setTimeout(TIMEOUT);
        singleServerConfig.setRetryAttempts(RETRY_ATTEMPTS);
        singleServerConfig.setDatabase(DATABASE);
        singleServerConfig.setClientName(redissonProperty.getClientName());
        if(StringUtils.isNotBlank(redissonProperty.getPassword())) {
            singleServerConfig.setPassword(redissonProperty.getPassword());
        }
        return Redisson.create(config);
    }

    @Bean
    @ConditionalOnProperty(name = "spring.redisson.server-mode", havingValue = "cluster")
    public RedissonClient clusterRedisson() {
        log.info("======================RedissonClient连接模式[cluster]=========================");
        Config config = new Config();
        List<String> nodeHosts = redissonProperty.getCluster().getNodeHosts();
        if(CollectionUtils.isEmpty(nodeHosts)) {
            throw new IllegalArgumentException("please config cluster node hosts !!! ");
        }
        ClusterServersConfig clusterServersConfig = config.useClusterServers();
        clusterServersConfig.addNodeAddress(toArray(getNodeHosts(nodeHosts)));
        clusterServersConfig.setClientName(redissonProperty.getClientName());
        clusterServersConfig.setMasterConnectionMinimumIdleSize(MASTER_CONNECTION_MINIMUM_IDLE_SIZE);
        clusterServersConfig.setSlaveConnectionMinimumIdleSize(SLAVE_CONNECTION_MINIMUM_IDLE_SIZE);
        clusterServersConfig.setIdleConnectionTimeout(IDLE_CONNECTION_TIMEOUT);
        clusterServersConfig.setConnectTimeout(CONNECT_TIMEOUT);
        clusterServersConfig.setTimeout(TIMEOUT);
        clusterServersConfig.setRetryAttempts(RETRY_ATTEMPTS);
        if(StringUtils.isNotBlank(redissonProperty.getPassword())) {
            clusterServersConfig.setPassword(redissonProperty.getPassword());
        }
        return Redisson.create(config);
    }

    @Bean
    @ConditionalOnProperty(name = "spring.redisson.server-mode", havingValue = "sentinel")
    public RedissonClient sentinelRedisson() {
        log.info("======================RedissonClient连接模式[sentinel]=========================");
        Config config = new Config();
        List<String> nodeHosts = redissonProperty.getSentinel().getNodeHosts();
        if(CollectionUtils.isEmpty(nodeHosts)) {
            throw new IllegalArgumentException("please config sentinel node hosts !!! ");
        }
        SentinelServersConfig sentinelServersConfig = config.useSentinelServers();
        sentinelServersConfig.setSentinelAddresses(getNodeHosts(nodeHosts));
        sentinelServersConfig.setMasterName(redissonProperty.getSentinel().getMasterName());
        sentinelServersConfig.setDatabase(DATABASE);
        sentinelServersConfig.setConnectTimeout(CONNECT_TIMEOUT);
        sentinelServersConfig.setTimeout(TIMEOUT);
        sentinelServersConfig.setRetryAttempts(RETRY_ATTEMPTS);
        if(StringUtils.isNotBlank(redissonProperty.getPassword())) {
            sentinelServersConfig.setPassword(redissonProperty.getPassword());
        }
        return Redisson.create(config);
    }

    /**
     * 功能描述: 获取redis服务列表
     */
    public List<String> getNodeHosts(List<String> hosts) {
        String redisPrefix = "redis://";
        List<String> nodes = new ArrayList<>();
        for (String node : hosts) {
            nodes.add(redisPrefix+node);
        }
        return nodes;
    }

    public String[] toArray(List<String> list){
        String[] strings = new String[list.size()];
        list.toArray(strings);
        return strings;
    }
}
