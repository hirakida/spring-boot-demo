package com.example;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

@SpringBootApplication
public class Server1Application {

    @Bean
    public RouterFunction<ServerResponse> routes() {
        return route(GET("/date"), Server1Application::date)
                .andRoute(GET("/datetime"), Server1Application::datetime);
    }

    private static Mono<ServerResponse> date(ServerRequest request) {
        return ok().contentType(MediaType.APPLICATION_STREAM_JSON)
                   .body(Mono.just(Map.of("date", LocalDate.now())), Map.class);
    }

    private static Mono<ServerResponse> datetime(ServerRequest request) {
        return ok().contentType(MediaType.APPLICATION_STREAM_JSON)
                   .body(Mono.just(Map.of("datetime", LocalDateTime.now())), Map.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Server1Application.class, args);
    }
}
