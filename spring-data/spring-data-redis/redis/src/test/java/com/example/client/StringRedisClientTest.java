package com.example.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Testcontainers
public class StringRedisClientTest {
    private static final String KEY1 = "__KEY1__";
    private static final String KEY2 = "__KEY2__";
    private static final String VALUE1 = "__VALUE1__";
    @Container
    private static final GenericContainer<?> CONTAINER = new GenericContainer<>("redis:5.0")
            .withExposedPorts(6379);
    @Autowired
    private StringRedisClient client;

    @DynamicPropertySource
    static void redisProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.redis.port", () -> CONTAINER.getMappedPort(6379));
    }

    @BeforeEach
    void setUp() {
        client.set(KEY1, VALUE1);
    }

    @Test
    public void get() {
        Optional<String> result = client.get(KEY1);
        assertTrue(result.isPresent());
        assertNotNull(result.get());
        assertEquals(Optional.of(VALUE1), result);

        result = client.get(KEY2);
        assertFalse(result.isPresent());
    }

    @Test
    public void set() {
        String key = "__KEY__";
        String value = "__VALUE__";
        Optional<String> result = client.get(key);
        assertFalse(result.isPresent());

        client.set(key, value);
        result = client.get(key);
        assertTrue(result.isPresent());
        assertEquals(Optional.of(value), result);
    }
}
