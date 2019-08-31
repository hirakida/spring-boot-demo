package com.example;

import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import com.fasterxml.jackson.databind.ObjectMapper;

import redis.embedded.RedisServer;

@TestConfiguration
@Import({ RedisConfig.class, RedisAutoConfiguration.class, ObjectMapper.class })
public class RedisTestConfig {

    @Bean(destroyMethod = "stop")
    public RedisServer redisServer(RedisProperties properties) {
        RedisServer redisServer = RedisServer.builder()
                                             .port(properties.getPort())
                                             .build();
        redisServer.start();
        return redisServer;
    }
}
