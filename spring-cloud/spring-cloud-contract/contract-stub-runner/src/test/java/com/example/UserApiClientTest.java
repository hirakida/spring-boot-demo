package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties.StubsMode;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest(webEnvironment = WebEnvironment.NONE,
        properties = "user-api.port=${stubrunner.runningstubs.contract-verifier.port}")
@AutoConfigureStubRunner(ids = "com.example:contract-verifier:+:stubs",
        stubsMode = StubsMode.LOCAL)
class UserApiClientTest {
    @Autowired
    private UserApiClient client;

    @Test
    void getUser() {
        final Mono<User> response = client.getUser(1);
        StepVerifier.create(response)
                    .expectNextMatches(result -> result != null
                                                 && result.getId() == 1
                                                 && "name1".equals(result.getName()))
                    .verifyComplete();
    }
}
