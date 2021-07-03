package com.example;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.servlet.function.RequestPredicates.accept;
import static org.springframework.web.servlet.function.ServerResponse.notFound;

import java.util.NoSuchElementException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class RouterConfig {
    @Bean
    public RouterFunction<ServerResponse> routes(UserHandler userHandler) {
        return RouterFunctions.route()
                              .path("/users", builder -> builder
                                      .GET("", accept(APPLICATION_JSON), userHandler::findAll)
                                      .GET("/{id}", accept(APPLICATION_JSON), userHandler::findById))
                              .onError(NoSuchElementException.class, (e, request) -> notFound().build())
                              .build();
    }
}
