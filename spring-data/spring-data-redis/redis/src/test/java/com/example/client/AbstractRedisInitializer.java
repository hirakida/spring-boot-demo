package com.example.client;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;

public abstract class AbstractRedisInitializer {
    @Container
    private static final GenericContainer<?> CONTAINER =
            new GenericContainer<>("redis:6.2")
                    .withExposedPorts(6379);

    @DynamicPropertySource
    static void redisProperties(DynamicPropertyRegistry registry) {
        CONTAINER.start();
        registry.add("spring.redis.port", () -> CONTAINER.getMappedPort(6379));
    }
}
