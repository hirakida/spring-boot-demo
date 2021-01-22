package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.Import;

import reactor.test.StepVerifier;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Import(R2dbcConfig.class)
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
