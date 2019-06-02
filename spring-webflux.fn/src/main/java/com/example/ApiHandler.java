package com.example;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.contentType;
import static org.springframework.web.reactive.function.server.ServerResponse.badRequest;
import static org.springframework.web.reactive.function.server.ServerResponse.notFound;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.example.core.User;
import com.example.core.UserRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ApiHandler {
    private final UserRepository userRepository;

    @Bean
    public RouterFunction<ServerResponse> routes() {
        return RouterFunctions.route(GET("/users/{id}").and(accept(APPLICATION_JSON)),
                                     this::findById)
                              .andRoute(GET("/users").and(accept(APPLICATION_JSON)),
                                        this::findAll)
                              .andRoute(POST("/users").and(contentType(APPLICATION_JSON)),
                                        this::create);
    }

    private Mono<ServerResponse> findById(ServerRequest request) {
        final String id = request.pathVariable("id");
        return userRepository.findById(id)
                             .flatMap(user -> ok().body(Mono.just(user), User.class))
                             .switchIfEmpty(notFound().build());
    }

    private Mono<ServerResponse> findAll(ServerRequest request) {
        final Flux<User> users = request.queryParam("name")
                                        .map(userRepository::findByName)
                                        .orElseGet(userRepository::findAll);
        return ok().body(users, User.class);
    }

    private Mono<ServerResponse> create(ServerRequest request) {
        final Mono<User> user = request.bodyToMono(User.class);
        return userRepository.saveAll(user)
                             .single()
                             .flatMap(res -> ok().build())
                             .switchIfEmpty(badRequest().build());
    }
}
