package com.example;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Flux;

@ActiveProfiles("test")
@WebFluxTest
@Import(UserHandler.class)
public class UserHandlerTest {
    @Autowired
    private WebTestClient webTestClient;
    @MockBean
    private UserRepository userRepository;

    @Test
    public void findAllTest() {
        when(userRepository.findAll()).thenReturn(Flux.just(new User("1", "name1", 21),
                                                            new User("2", "name2", 22),
                                                            new User("3", "name3", 23)));
        String expectedJson = '[' +
                              "{\"id\":\"1\",\"name\":\"name1\",\"age\":21}," +
                              "{\"id\":\"2\",\"name\":\"name2\",\"age\":22}," +
                              "{\"id\":\"3\",\"name\":\"name3\",\"age\":23}" +
                              ']';
        webTestClient.get()
                     .uri("http://localhost:8080/users")
                     .exchange()
                     .expectHeader().contentType(MediaType.APPLICATION_JSON)
                     .expectStatus().isOk()
                     .expectBody().json(expectedJson);
    }
}
