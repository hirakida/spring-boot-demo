package com.example;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;

import io.restassured.RestAssured;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, properties = "server.port=0")
class BaseClass {
    @LocalServerPort
    private int port;
    @MockitoBean
    private UserService userService;

    @BeforeEach
    void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
        when(userService.getUser(1)).thenReturn(Mono.just(new User(1, "name1")));
    }
}
