package com.example;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@DataMongoTest
@Testcontainers
public class UserRepositoryTest {
    @Container
    private static final GenericContainer<?> CONTAINER =
            new GenericContainer<>(DockerImageName.parse("mongo:4.4"))
                    .withEnv("MONGO_INITDB_ROOT_USERNAME", "root")
                    .withEnv("MONGO_INITDB_ROOT_PASSWORD", "pass")
                    .withEnv("MONGO_INITDB_DATABASE", "admin")
                    .withExposedPorts(27017);
    @Autowired
    private UserRepository userRepository;

    @DynamicPropertySource
    static void mongodbProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.host", CONTAINER::getHost);
        registry.add("spring.data.mongodb.port", () -> CONTAINER.getMappedPort(27017));
    }

    @BeforeEach
    public void init() {
        userRepository.deleteAll();
        List<User> users = IntStream.rangeClosed(1, 5)
                                    .mapToObj(i -> new User("name" + i))
                                    .collect(toList());
        userRepository.saveAll(users);
    }

    @Test
    public void findAll() {
        List<User> result = userRepository.findAll();
        assertEquals(5, result.size());
    }

    @Test
    public void findByName() {
        List<User> result = userRepository.findByName("name1");
        assertEquals(1, result.size());
    }
}
