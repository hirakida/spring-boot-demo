package com.example;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@DataMongoTest
@Testcontainers
@ContextConfiguration(initializers = MongoContextInitializer.class)
public class UserRepositoryTest {
    @Container
    private static final GenericContainer<?> CONTAINER = MongoContextInitializer.CONTAINER;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();
        List<User> users = IntStream.rangeClosed(1, 5)
                                    .mapToObj(i -> new User("name" + i))
                                    .collect(toList());
        userRepository.saveAll(users);
    }

    @Test
    public void queryMethods() {
        List<User> result = userRepository.findAll();
        assertEquals(5, result.size());

        result = userRepository.findByName("name1");
        assertEquals(1, result.size());

        result = userRepository.findByUsername("name1");
        assertEquals(1, result.size());
    }
}
