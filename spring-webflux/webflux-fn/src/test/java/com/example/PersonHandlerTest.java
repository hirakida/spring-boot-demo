package com.example;

import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Flux;

@WebFluxTest
@Import(PersonHandler.class)
class PersonHandlerTest {
    @Autowired
    private WebTestClient client;
    @MockBean
    private PersonRepository repository;

    @Test
    void findAll() {
        when(repository.findAll())
                .thenReturn(Flux.just(new Person(1, "name1", LocalDateTime.of(2020, 12, 1, 0, 0)),
                                      new Person(2, "name2", LocalDateTime.of(2020, 12, 1, 0, 0)),
                                      new Person(3, "name3", LocalDateTime.of(2020, 12, 1, 0, 0))));
        String expectedJson = '[' +
                              "{\"id\":1,\"name\":\"name1\",\"createdAt\":\"2020-12-01T00:00:00\"}," +
                              "{\"id\":2,\"name\":\"name2\",\"createdAt\":\"2020-12-01T00:00:00\"}," +
                              "{\"id\":3,\"name\":\"name3\",\"createdAt\":\"2020-12-01T00:00:00\"}" +
                              ']';
        client.get()
              .uri("http://localhost:8080/persons")
              .exchange()
              .expectHeader().contentType(MediaType.APPLICATION_JSON)
              .expectStatus().isOk()
              .expectBody().json(expectedJson);
    }
}
