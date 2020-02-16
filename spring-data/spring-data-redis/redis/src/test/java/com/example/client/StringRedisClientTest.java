package com.example.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.example.RedisInitializer;
import com.example.RedisTestConfig;

@Testcontainers
@ExtendWith(SpringExtension.class)
@Import(RedisTestConfig.class)
@ContextConfiguration(classes = StringRedisClient.class, initializers = RedisInitializer.class)
public class StringRedisClientTest {
    static final String KEY1 = "__KEY1__";
    static final String KEY2 = "__KEY2__";
    static final String VALUE1 = "__VALUE1__";

    @Container
    private static final GenericContainer<?> CONTAINER = RedisInitializer.getContainer();
    @Autowired
    private StringRedisClient target;

    @BeforeEach
    void setUp() {
        target.set(KEY1, VALUE1);
    }

    @Test
    public void getTest() {
        Optional<String> result = target.get(KEY1);
        assertTrue(result.isPresent());
        assertEquals(Optional.of(VALUE1), result);

        result = target.get(KEY2);
        assertFalse(result.isPresent());
    }

    @Test
    public void setTest() {
        String key = "__KEY__";
        String value = "__VALUE__";
        Optional<String> result = target.get(key);
        assertFalse(result.isPresent());

        target.set(key, value);
        result = target.get(key);
        assertTrue(result.isPresent());
        assertEquals(Optional.of(value), result);
    }
}
