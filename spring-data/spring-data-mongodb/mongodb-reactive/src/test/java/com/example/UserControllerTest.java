package com.example;

import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Flux;

@WebFluxTest
public class UserControllerTest {
    @Autowired
    private WebTestClient client;
    @MockBean
    private UserRepository userRepository;

    @Test
    public void findAll() {
        LocalDateTime now = LocalDateTime.parse("2019-11-09T10:00:00");
        List<User> users = List.of(new User("1", "name1", 21, now, now, 1L),
                                   new User("2", "name2", 22, now, now, 1L),
                                   new User("3", "name3", 23, now, now, 1L));
        when(userRepository.findAll()).thenReturn(Flux.fromIterable(users));

        client.get()
              .uri("http://localhost:8080/users")
              .exchange()
              .expectHeader().contentType(MediaType.APPLICATION_JSON)
              .expectStatus().isOk()
              .expectBody()
              .jsonPath("$[0].id").isEqualTo(1)
              .jsonPath("$[0].name").isEqualTo("name1")
              .jsonPath("$[0].age").isEqualTo(21)
              .jsonPath("$[1].id").isEqualTo(2)
              .jsonPath("$[1].name").isEqualTo("name2")
              .jsonPath("$[1].age").isEqualTo(22)
              .jsonPath("$[2].id").isEqualTo(3)
              .jsonPath("$[2].name").isEqualTo("name3")
              .jsonPath("$[2].age").isEqualTo(23);
    }
}
