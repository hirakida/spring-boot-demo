package com.example.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.example.RedisConfig;
import com.example.User;
import com.redis.testcontainers.RedisContainer;

@DataRedisTest
@Import({ UserRedisClient.class, RedisConfig.class, JacksonAutoConfiguration.class })
@Testcontainers
class UserRedisClientTest {
    private static final Clock CLOCK = Clock.fixed(Instant.parse("2021-01-01T00:00:00Z"),
                                                   ZoneId.systemDefault());
    @Container
    @ServiceConnection
    private static final RedisContainer CONTAINER = new RedisContainer("redis:7.0");
    @Autowired
    private UserRedisClient client;

    @BeforeEach
    void setUp() {
        User user = new User(1, "name1", LocalDateTime.now(CLOCK), LocalDateTime.now(CLOCK));
        client.set(1, user);
    }

    @Test
    void get() {
        User expected = new User(1, "name1", LocalDateTime.now(CLOCK), LocalDateTime.now(CLOCK));
        Optional<User> actual = client.get(1);
        assertTrue(actual.isPresent());
        assertEquals(expected, actual.get());
    }
}
