package com.example;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.servlet.function.RequestPredicates.GET;
import static org.springframework.web.servlet.function.RequestPredicates.accept;
import static org.springframework.web.servlet.function.ServerResponse.notFound;
import static org.springframework.web.servlet.function.ServerResponse.ok;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ApiHandler {
    private final UserRepository userRepository;

    @Bean
    public RouterFunction<ServerResponse> routes() {
        return RouterFunctions.route(GET("/users/{id}").and(accept(APPLICATION_JSON)),
                                     this::findById)
                              .andRoute(GET("/users").and(accept(APPLICATION_JSON)),
                                        this::findAll);
    }

    private ServerResponse findById(ServerRequest request) {
        final int id = Integer.parseInt(request.pathVariable("id"));
        return userRepository.findById(id)
                             .map(user -> ok().body(user))
                             .orElse(notFound().build());
    }

    private ServerResponse findAll(ServerRequest request) {
        return ok().body(userRepository.findAll());
    }
}
