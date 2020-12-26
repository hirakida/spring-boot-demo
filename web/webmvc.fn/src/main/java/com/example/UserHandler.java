package com.example;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.servlet.function.RequestPredicates.accept;
import static org.springframework.web.servlet.function.ServerResponse.notFound;
import static org.springframework.web.servlet.function.ServerResponse.ok;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserHandler {
    private final UserRepository userRepository;

    @Bean
    public RouterFunction<ServerResponse> routes() {
        return RouterFunctions.route()
                              .path("/users", builder -> builder
                                      .GET("", accept(APPLICATION_JSON), this::findAll)
                                      .GET("/{id}", accept(APPLICATION_JSON), this::findById))
                              .onError(NoSuchElementException.class, (e, request) -> notFound().build())
                              .build();
    }

    public ServerResponse findById(ServerRequest request) {
        int id = Integer.parseInt(request.pathVariable("id"));
        return userRepository.findById(id)
                             .map(user -> ok().body(user))
                             .orElse(notFound().build());
    }

    public ServerResponse findAll(ServerRequest request) {
        List<User> users = userRepository.findAll();
        return ok().body(users);
    }
}
