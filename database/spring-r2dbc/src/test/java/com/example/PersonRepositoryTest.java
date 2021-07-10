package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import reactor.test.StepVerifier;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class PersonRepositoryTest {
    @Autowired
    private PersonRepository repository;

    @Test
    public void findAll() {
        StepVerifier.create(repository.findAll())
                    .expectNextCount(5)
                    .verifyComplete();
    }
}
