package com.example;

import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Flux;

@WebFluxTest
@ActiveProfiles("test")
public class UserControllerTest {
    @Autowired
    private WebTestClient webTestClient;
    @MockBean
    private UserRepository userRepository;

    @Test
    public void findAllTest() {
        LocalDateTime datetime = LocalDateTime.parse("2019-11-09T10:00:00");
        List<User> users = List.of(new User("1", "name1", 21, datetime, datetime, 1L),
                                   new User("2", "name2", 22, datetime, datetime, 1L),
                                   new User("3", "name3", 23, datetime, datetime, 1L));
        String expectedJson =
                '['
                + "{\"id\":\"1\",\"name\":\"name1\",\"age\":21,\"createdAt\":\"2019-11-09T10:00:00\",\"updatedAt\":\"2019-11-09T10:00:00\",\"version\":1},"
                + "{\"id\":\"2\",\"name\":\"name2\",\"age\":22,\"createdAt\":\"2019-11-09T10:00:00\",\"updatedAt\":\"2019-11-09T10:00:00\",\"version\":1},"
                + "{\"id\":\"3\",\"name\":\"name3\",\"age\":23,\"createdAt\":\"2019-11-09T10:00:00\",\"updatedAt\":\"2019-11-09T10:00:00\",\"version\":1}"
                + ']';
        when(userRepository.findAll()).thenReturn(Flux.fromIterable(users));

        webTestClient.get()
                     .uri("http://localhost:8080/users")
                     .exchange()
                     .expectHeader().contentType(MediaType.APPLICATION_JSON)
                     .expectStatus().isOk()
                     .expectBody().json(expectedJson);
    }
}
