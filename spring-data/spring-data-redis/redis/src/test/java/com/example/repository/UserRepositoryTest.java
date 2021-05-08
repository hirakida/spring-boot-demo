package com.example.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;

import com.example.User;

@DataRedisTest
public class UserRepositoryTest {
    private static final Clock CLOCK = Clock.fixed(Instant.parse("2021-01-01T00:00:00Z"),
                                                   ZoneId.systemDefault());
    @Autowired
    private UserRepository repository;

    @BeforeEach
    public void setUp() {
        repository.deleteAll();
        User user1 = new User(1, "name1", LocalDateTime.now(CLOCK), LocalDateTime.now(CLOCK));
        User user2 = new User(2, "name2", LocalDateTime.now(CLOCK), LocalDateTime.now(CLOCK));
        repository.saveAll(List.of(user1, user2));
    }

    @Test
    public void findAll() {
        Iterable<User> actual = repository.findAll();
        assertEquals(2, ((Collection<User>) actual).size());
    }

    @Test
    public void findById() {
        User expected = new User(1, "name1", LocalDateTime.now(CLOCK), LocalDateTime.now(CLOCK));
        Optional<User> actual = repository.findById(1);
        assertTrue(actual.isPresent());
        assertEquals(expected, actual.get());

        Optional<User> empty = repository.findById(3);
        assertTrue(empty.isEmpty());
    }
}
