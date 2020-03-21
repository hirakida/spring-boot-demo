package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import reactor.test.StepVerifier;

@DataMongoTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void createTest() {
        User user = new User();
        user.setName("name1");
        user.setAge(30);

        StepVerifier.create(userRepository.save(user))
                    .expectNextMatches(result -> result.getId() != null
                                                 && "name1".equals(result.getName()))
                    .verifyComplete();
    }
}
