package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest
public class ApiControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void hello() {
        webTestClient.get()
                     .uri("http://localhost:8080/")
                     .exchange()
                     .expectHeader().contentType(MediaType.APPLICATION_JSON)
                     .expectStatus().isOk()
                     .expectBody().json("{\"message\":\"Hello, WebFlux!\"}");
    }
}
