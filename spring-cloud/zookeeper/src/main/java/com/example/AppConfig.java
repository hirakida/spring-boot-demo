package com.example;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    private static final String LATCH_PATH = "/latch";

    @Value("${server.address:localhost}")
    private String address;

    @Value("${server.port:8080}")
    private String port;

    @Bean(destroyMethod = "close")
    public LeaderLatch leaderLatch(CuratorFramework curatorFramework) {
        String id = address + ':' + port;
        LeaderLatch leaderLatch = new LeaderLatch(curatorFramework, LATCH_PATH, id);
        try {
            leaderLatch.start();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return leaderLatch;
    }
}
