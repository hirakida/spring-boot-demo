package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@DataRedisTest
@Import({
        UserRedisClient.class,
        JacksonAutoConfiguration.class,
        RedisConfig.class
})
@Testcontainers
class UserRedisClientTest {
    private static final Clock CLOCK = Clock.fixed(Instant.parse("2021-01-01T00:00:00Z"),
                                                   ZoneId.systemDefault());
    @Container
    @ServiceConnection
    private static final GenericContainer<?> CONTAINER =
            new GenericContainer<>("redis:7.0").withExposedPorts(6379);
    @Autowired
    private UserRedisClient client;

    @BeforeEach
    void setUp() {
        final User user = new User(1, "name1", LocalDateTime.now(CLOCK), LocalDateTime.now(CLOCK));
        client.set("user1", user).block();
    }

    @Test
    void get() {
        final User expected = new User(1, "name1", LocalDateTime.now(CLOCK), LocalDateTime.now(CLOCK));
        final User actual = client.get("user1").block();
        assertEquals(expected, actual);
    }
}
