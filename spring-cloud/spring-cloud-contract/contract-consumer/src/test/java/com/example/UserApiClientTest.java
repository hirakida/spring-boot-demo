package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties.StubsMode;

import com.example.UserApiClient.User;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@AutoConfigureStubRunner(ids = "com.example:contract-producer:0.0.1-SNAPSHOT:stubs:8080",
        stubsMode = StubsMode.LOCAL)
public class UserApiClientTest {
    @Autowired
    private UserApiClient client;

    @Test
    public void getUserTest() {
        Mono<User> response = client.getUser(1);
        StepVerifier.create(response)
                    .expectNextMatches(result -> result != null
                                                 && result.getId() == 1
                                                 && "name1".equals(result.getName()))
                    .verifyComplete();
    }
}
