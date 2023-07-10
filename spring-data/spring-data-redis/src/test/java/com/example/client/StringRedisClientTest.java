package com.example.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Testcontainers
class StringRedisClientTest {
    private static final String KEY1 = "__KEY1__";
    private static final String KEY2 = "__KEY2__";
    private static final String VALUE1 = "__VALUE1__";
    private static final String VALUE2 = "__VALUE2__";
    @Container
    @ServiceConnection
    private static final GenericContainer<?> CONTAINER =
            new GenericContainer<>("redis:7.0").withExposedPorts(6379);
    @Autowired
    private StringRedisClient client;

    @BeforeEach
    void setUp() {
        client.set(KEY1, VALUE1);
        client.delete(KEY2);
    }

    @Test
    void get() {
        Optional<String> result = client.get(KEY1);
        assertTrue(result.isPresent());
        assertEquals(VALUE1, result.get());

        result = client.get(KEY2);
        assertTrue(result.isEmpty());
    }

    @Test
    void set() {
        String key = "__KEY__";
        String value = "__VALUE__";
        Optional<String> result = client.get(key);
        assertFalse(result.isPresent());

        client.set(key, value);
        result = client.get(key);
        assertTrue(result.isPresent());
        assertEquals(Optional.of(value), result);
    }

    @Test
    void exists() {
        assertTrue(client.exists(KEY1, VALUE1));
        assertFalse(client.exists(KEY2, VALUE1));
    }

    @Test
    void checkAndSet() {
        assertTrue(client.checkAndSet(KEY1, VALUE1, VALUE2));
        Optional<String> result = client.get(KEY1);
        assertTrue(result.isPresent());
        assertEquals(VALUE2, result.get());

        assertFalse(client.checkAndSet(KEY2, VALUE1, VALUE2));
    }
}
