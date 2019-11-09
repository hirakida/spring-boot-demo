package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties.StubsMode;

import com.example.RandomApiClient.Random;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@AutoConfigureStubRunner(ids = "com.example:contract-producer:+:8080", stubsMode = StubsMode.LOCAL)
public class RandomApiClientTest {
    @Autowired
    private RandomApiClient client;

    @Test
    public void getRandomTest() {
        Mono<Random> response = client.getRandom();
        StepVerifier.create(response)
                    .expectNextMatches(result -> result != null
                                                 && result.getRandom() == 1234567890L)
                    .verifyComplete();
    }
}
