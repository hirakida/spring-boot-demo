package com.example;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.example.model.User;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest(webEnvironment = WebEnvironment.NONE,
        properties = "github.base-url=http://localhost:${wiremock.server.port}")
@AutoConfigureWireMock(port = 0)
class GitHubClientTest {
    @Autowired
    private GitHubClient client;

    @Test
    void getUser() {
        String body = "{\"login\":\"hirakida\",\"id\":100}";
        stubFor(get(urlEqualTo("/users/hirakida"))
                        .willReturn(aResponse()
                                            .withBody(body)
                                            .withHeader(HttpHeaders.CONTENT_TYPE,
                                                        MediaType.APPLICATION_JSON_VALUE)
                                            .withStatus(HttpStatus.OK.value())));

        Mono<User> user = client.getUser("hirakida");
        StepVerifier.create(user)
                    .expectNextMatches(result -> result != null
                                                 && "hirakida".equals(result.login())
                                                 && result.id() == 100)
                    .verifyComplete();
    }
}
