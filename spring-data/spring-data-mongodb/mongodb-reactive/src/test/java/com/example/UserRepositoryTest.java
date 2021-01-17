package com.example;

import static java.util.stream.Collectors.toList;

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

import reactor.test.StepVerifier;

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
    public void setUp() {
        List<User> users = IntStream.rangeClosed(1, 5)
                                    .mapToObj(i -> new User("name" + i, 20 + i))
                                    .collect(toList());
        userRepository.deleteAll()
                      .thenMany(userRepository.saveAll(users))
                      .blockLast();
    }

    @Test
    public void findByName() {
        StepVerifier.create(userRepository.findByName("name1"))
                    .expectNextMatches(result -> "name1".equals(result.getName())
                                                 && result.getAge() == 21)
                    .verifyComplete();
    }

    @Test
    public void save() {
        User user = new User();
        user.setName("name6");
        user.setAge(30);

        StepVerifier.create(userRepository.save(user))
                    .expectNextMatches(result -> result.getId() != null
                                                 && "name6".equals(result.getName()))
                    .verifyComplete();
    }
}
