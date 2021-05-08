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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.example.User;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class UserRedisClientTest extends AbstractRedisInitializer {
    private static final Clock CLOCK = Clock.fixed(Instant.parse("2021-01-01T00:00:00Z"),
                                                   ZoneId.systemDefault());
    @Autowired
    private UserRedisClient client;

    @BeforeEach
    void setUp() {
        User user = new User(1, "name1", LocalDateTime.now(CLOCK), LocalDateTime.now(CLOCK));
        client.set(1, user);
    }

    @Test
    public void get() {
        User expected = new User(1, "name1", LocalDateTime.now(CLOCK), LocalDateTime.now(CLOCK));
        Optional<User> actual = client.get(1);
        assertTrue(actual.isPresent());
        assertEquals(expected, actual.get());
    }
}
