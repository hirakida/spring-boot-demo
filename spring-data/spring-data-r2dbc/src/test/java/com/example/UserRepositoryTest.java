package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.context.annotation.Import;

import com.example.config.R2dbcConfig;

import reactor.test.StepVerifier;

@DataR2dbcTest
@Import(R2dbcConfig.class)
public class UserRepositoryTest {
    @Autowired
    private UserRepository repository;

    @Test
    public void findByName() {
        StepVerifier.create(repository.findByName("user1"))
                    .expectNextMatches(result -> "user1".equals(result.getName()))
                    .verifyComplete();
    }
}
