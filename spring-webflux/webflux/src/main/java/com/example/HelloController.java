package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class HelloController {

    @GetMapping("/hello1")
    public Mono<Response> hello1() {
        return Mono.just(new Response("Hello!"));
    }

    @GetMapping("/hello2")
    public Flux<Response> hello2() {
        return Flux.just("Hello!", "Hello!!", "Hello!!!")
                   .map(Response::new);
    }

    @Data
    @AllArgsConstructor
    public static class Response {
        private String message;
    }
}
