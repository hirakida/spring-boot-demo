package com.example;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;

import io.restassured.RestAssured;
import reactor.core.publisher.Mono;

@SpringBootTest(properties = "server.port=0", webEnvironment = WebEnvironment.RANDOM_PORT)
public class BaseClass {
    @LocalServerPort
    private int port;
    @MockBean
    private RandomService randomService;

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "http://localhost:" + port;
        when(randomService.getRandom()).thenReturn(Mono.just(1234567890L));
    }
}
