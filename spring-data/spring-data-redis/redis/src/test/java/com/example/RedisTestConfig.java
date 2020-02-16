package com.example;

import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;

import com.example.config.RedisConfig;
import com.fasterxml.jackson.databind.ObjectMapper;

@TestConfiguration
@Import({ RedisConfig.class, RedisAutoConfiguration.class, ObjectMapper.class })
public class RedisTestConfig {
}
