package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest
class HelloControllerTest {
    @Autowired
    private WebTestClient client;

    @Test
    void hello1() {
        client.get()
              .uri("/hello1")
              .exchange()
              .expectHeader().contentType(MediaType.APPLICATION_JSON)
              .expectStatus().isOk()
              .expectBody()
              .json("{\"message\":\"Hello!\"}");
    }

    @Test
    void hello2() {
        client.get()
              .uri("/hello2")
              .exchange()
              .expectHeader().contentType(MediaType.APPLICATION_JSON)
              .expectStatus().isOk()
              .expectBody()
              .jsonPath("$[0].message").isEqualTo("Hello!")
              .jsonPath("$[1].message").isEqualTo("Hello!!")
              .jsonPath("$[2].message").isEqualTo("Hello!!!");
    }
}
