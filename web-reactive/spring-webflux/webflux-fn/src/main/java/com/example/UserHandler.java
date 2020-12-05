package com.example;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.contentType;
import static org.springframework.web.reactive.function.server.ServerResponse.badRequest;
import static org.springframework.web.reactive.function.server.ServerResponse.created;
import static org.springframework.web.reactive.function.server.ServerResponse.notFound;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import java.net.URI;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UserHandler {
    private final UserRepository userRepository;

    @Bean
    public RouterFunction<ServerResponse> routes() {
        return RouterFunctions.route()
                              .path("/users", builder -> builder
                                      .GET("", accept(APPLICATION_JSON), this::findAll)
                                      .GET("/{id}", accept(APPLICATION_JSON), this::findById)
                                      .POST("", contentType(APPLICATION_JSON), this::create))
                              .build();
    }

    private Mono<ServerResponse> findAll(ServerRequest request) {
        Flux<User> users = request.queryParam("name")
                                  .map(userRepository::findByName)
                                  .orElseGet(userRepository::findAll);
        return ok().body(users, User.class);
    }

    private Mono<ServerResponse> findById(ServerRequest request) {
        String id = request.pathVariable("id");
        return userRepository.findById(id)
                             .flatMap(user -> ok().body(fromValue(user)))
                             .switchIfEmpty(notFound().build());
    }

    private Mono<ServerResponse> create(ServerRequest request) {
        Mono<User> user = request.bodyToMono(User.class);
        return userRepository.saveAll(user)
                             .single()
                             .flatMap(created -> created(URI.create("/users/" + created.getId())).build())
                             .switchIfEmpty(badRequest().build());
    }
}
