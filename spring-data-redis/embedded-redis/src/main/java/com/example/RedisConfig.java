package com.example;

import java.io.IOException;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.embedded.RedisServer;

@Configuration
public class RedisConfig {

    @Bean(destroyMethod = "stop")
    public RedisServer redisServer(RedisProperties redisProperties) throws IOException {
        RedisServer redisServer = new RedisServer(redisProperties.getPort());
        redisServer.start();
        return redisServer;
    }
}
