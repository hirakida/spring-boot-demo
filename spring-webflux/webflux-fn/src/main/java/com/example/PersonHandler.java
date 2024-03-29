package com.example;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.contentType;
import static org.springframework.web.reactive.function.server.RequestPredicates.queryParam;
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

import lombok.Data;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class PersonHandler {
    private final PersonRepository repository;

    @Bean
    public RouterFunction<ServerResponse> routes() {
        return RouterFunctions.route()
                              .path("/persons", builder1 -> builder1
                                      .nest(accept(APPLICATION_JSON), builder2 -> builder2
                                              .GET("/{id}", this::findById)
                                              .GET(queryParam("name", param -> true), this::findByName)
                                              .GET(this::findAll))
                                      .POST(contentType(APPLICATION_JSON), this::create))
                              .build();
    }

    private Mono<ServerResponse> findAll(ServerRequest request) {
        return ok().body(repository.findAll(), Person.class);
    }

    private Mono<ServerResponse> findById(ServerRequest request) {
        int id = Integer.parseInt(request.pathVariable("id"));
        return repository.findById(id)
                         .flatMap(person -> ok().bodyValue(person))
                         .switchIfEmpty(notFound().build());
    }

    private Mono<ServerResponse> findByName(ServerRequest request) {
        Flux<Person> persons = request.queryParam("name")
                                      .map(repository::findByName)
                                      .orElseGet(Flux::empty);
        return ok().body(persons, Person.class);
    }

    private Mono<ServerResponse> create(ServerRequest request) {
        Mono<Person> person = request.bodyToMono(Request.class)
                                     .map(req -> new Person(req.getName()));
        return repository.saveAll(person)
                         .single()
                         .flatMap(created -> created(URI.create("/persons/" + created.getId())).build())
                         .switchIfEmpty(badRequest().build());
    }

    @Data
    private static class Request {
        private String name;
    }
}
