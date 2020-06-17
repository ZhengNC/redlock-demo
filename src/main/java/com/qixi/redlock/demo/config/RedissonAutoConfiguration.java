package com.qixi.redlock.demo.config;

import com.qixi.redlock.demo.util.RedLockUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

/**
 * @author ZhengNC
 * @date 2020/6/4 18:53
 */
@Configuration
public class RedissonAutoConfiguration {

    @Value("${spring.redis.cluster.nodes}")
    private List<String> clusterNodes;

    @Value("${spring.redis.password}")
    private String password;

    @Bean
    public RedLockUtils getRedLockUtils(){
        String[] nodes = new String[clusterNodes.size()];
        for (int i = 0; i < nodes.length; i++){
            nodes[i] = "redis://"+clusterNodes.get(i);
        }
        Config config = new Config();
        config.useClusterServers()
                .addNodeAddress(nodes)
                .setScanInterval(2000)
                .setPassword(password);
        RedissonClient redissonClient = Redisson.create(config);
        RedLockUtils redLockUtils = new RedLockUtils(redissonClient);
        return redLockUtils;
    }
}