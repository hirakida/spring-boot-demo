package com.example;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.servlet.function.RequestPredicates.accept;
import static org.springframework.web.servlet.function.ServerResponse.notFound;

import java.util.NoSuchElementException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

@SpringBootApplication
public class Application {
    @Bean
    public RouterFunction<ServerResponse> routes(UserHandler userHandler) {
        return RouterFunctions.route()
                              .path("/users", builder -> builder
                                      .GET("", accept(APPLICATION_JSON), userHandler::findAll)
                                      .GET("/{id}", accept(APPLICATION_JSON), userHandler::findById))
                              .onError(NoSuchElementException.class, (e, request) -> notFound().build())
                              .build();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
