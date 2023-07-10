package com.example;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import reactor.test.StepVerifier;

@DataMongoTest
@Testcontainers
class UserRepositoryTest {
    @Container
    @ServiceConnection
    private static final GenericContainer<?> CONTAINER = new MongoDBContainer("mongo:5.0");
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        List<User> users = IntStream.rangeClosed(1, 5)
                                    .mapToObj(i -> new User("name" + i, 20 + i))
                                    .collect(toList());
        userRepository.deleteAll()
                      .thenMany(userRepository.saveAll(users))
                      .blockLast();
    }

    @Test
    void findByName() {
        StepVerifier.create(userRepository.findByName("name1"))
                    .expectNextMatches(result -> "name1".equals(result.getName())
                                                 && result.getAge() == 21)
                    .verifyComplete();
    }

    @Test
    void save() {
        User user = new User();
        user.setName("name6");
        user.setAge(30);

        StepVerifier.create(userRepository.save(user))
                    .expectNextMatches(result -> result.getId() != null
                                                 && "name6".equals(result.getName()))
                    .verifyComplete();
    }
}
