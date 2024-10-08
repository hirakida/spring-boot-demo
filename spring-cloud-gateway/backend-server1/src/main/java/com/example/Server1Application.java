package com.example;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@SpringBootApplication
public class Server1Application {
    @Bean
    public RouterFunction<ServerResponse> routes() {
        return route(GET("/datetime"),
                     request -> ok().bodyValue(Map.of("datetime", LocalDateTime.now())));
    }

    public static void main(String[] args) {
        SpringApplication.run(Server1Application.class, args);
    }
}
