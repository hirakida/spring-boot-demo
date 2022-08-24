package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.context.annotation.Import;

import com.example.config.R2dbcConfig;

import reactor.test.StepVerifier;

@DataR2dbcTest
@Import({ R2dbcConfig.class, AccountClient.class })
class AccountClientTest {
    @Autowired
    private AccountClient client;

    @Test
    void findAll() {
        StepVerifier.create(client.findAll())
                    .expectNextCount(5)
                    .verifyComplete();
    }
}
