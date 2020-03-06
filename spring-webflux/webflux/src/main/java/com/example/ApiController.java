package com.example;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Value;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ApiController {

    @GetMapping("/")
    public Mono<Map<String, String>> hello() {
        return Mono.just(Map.of("message", "Hello, WebFlux!"));
    }

    @GetMapping(path = "/sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Response> sse() {
        return Flux.fromStream(Stream.generate(() -> new Response(LocalTime.now())))
                   .delayElements(Duration.ofSeconds(1));
    }

    @GetMapping(path = "/json_stream", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Response> jsonStream() {
        return Flux.fromStream(Stream.generate(() -> new Response(LocalTime.now())))
                   .delayElements(Duration.ofSeconds(1));
    }

    @Value
    public static class Response {
        private final LocalTime time;
    }
}
