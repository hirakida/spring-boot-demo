package com.example;

import java.time.Duration;
import java.time.LocalTime;
import java.util.stream.Stream;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class HelloController {

    @GetMapping("/mono")
    public Mono<HelloResponse> mono() {
        return Mono.just(new HelloResponse("Hello, WebFlux!"));
    }

    @GetMapping("/flux")
    public Flux<HelloResponse> flux() {
        return Flux.just(new HelloResponse("Hello,"),
                         new HelloResponse("WebFlux!"));
    }

    @GetMapping(path = "/sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<StreamResponse> sse() {
        return Flux.fromStream(Stream.generate(() -> new StreamResponse(LocalTime.now())))
                   .delayElements(Duration.ofSeconds(1))
                   .take(10);
    }

    @GetMapping(path = "/ndjson", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<StreamResponse> ndjson() {
        return Flux.fromStream(Stream.generate(() -> new StreamResponse(LocalTime.now())))
                   .delayElements(Duration.ofSeconds(1))
                   .take(10);
    }

    @Data
    @AllArgsConstructor
    public static class HelloResponse {
        private String message;
    }

    @Data
    @AllArgsConstructor
    public static class StreamResponse {
        private LocalTime time;
    }
}
