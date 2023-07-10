package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;

import reactor.test.StepVerifier;

@DataR2dbcTest
class PersonRepositoryTest {
    @Autowired
    private PersonRepository repository;

    @Test
    void findByName() {
        StepVerifier.create(repository.findByName("person1"))
                    .expectNextMatches(result -> "person1".equals(result.getName()))
                    .verifyComplete();
    }
}
