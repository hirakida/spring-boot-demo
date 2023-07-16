package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.LongStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Testcontainers
class UserRepositoryTest {
    @Container
    private static final GenericContainer<?> CONTAINER =
            new GenericContainer<>("hazelcast/hazelcast:5").withExposedPorts(5701);
    @Autowired
    private UserRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
        List<User> users = LongStream.rangeClosed(1, 5)
                                     .mapToObj(i -> {
                                         User user = new User();
                                         user.setUserId(i);
                                         user.setName("user" + i);
                                         user.setCreatedAt(LocalDateTime.now());
                                         return user;
                                     })
                                     .toList();
        repository.saveAll(users);
    }

    @Test
    void findAll() {
        List<User> actual = repository.findAll();
        assertEquals(5, actual.size());
    }
}
