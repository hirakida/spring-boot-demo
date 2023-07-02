package com.example;

import java.time.Duration;
import java.time.LocalTime;
import java.util.stream.Stream;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping(path = "/sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<StreamResponse> sse() {
        return Flux.fromStream(Stream.generate(() -> new StreamResponse(LocalTime.now())))
                   .delayElements(Duration.ofMillis(100))
                   .take(10);
    }

    @GetMapping(path = "/ndjson", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<StreamResponse> ndjson() {
        return Flux.fromStream(Stream.generate(() -> new StreamResponse(LocalTime.now())))
                   .delayElements(Duration.ofMillis(100))
                   .take(10);
    }

    public record Response(String message) {}

    public record StreamResponse(LocalTime time) {}
}
