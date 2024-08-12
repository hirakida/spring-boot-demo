package com.example;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CuratorConfig {
    private static final String LATCH_PATH = "/latch";

    @Bean(destroyMethod = "close")
    public LeaderLatch leaderLatch(CuratorFramework curatorFramework,
                                   @Value("${server.address:localhost}") String address,
                                   @Value("${server.port:8080}") String port) throws Exception {
        LeaderLatch leaderLatch = new LeaderLatch(curatorFramework, LATCH_PATH, address + ':' + port);
        leaderLatch.start();
        return leaderLatch;
    }
}
