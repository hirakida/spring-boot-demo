package com.example.client;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.example.model.User;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest(properties = "github.base-url=http://localhost:8081")
@AutoConfigureWireMock(port = 8081)
public class GitHubApiClientTest {
    @Autowired
    private GitHubApiClient client;

    @Test
    public void getUser() {
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
                                                 && "hirakida".equals(result.getLogin())
                                                 && result.getId() == 100)
                    .verifyComplete();
    }
}
