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

@RestController
public class StreamController {
    @GetMapping(path = "/sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Response> sse() {
        return Flux.fromStream(Stream.generate(() -> new Response(LocalTime.now())))
                   .delayElements(Duration.ofMillis(100))
                   .take(10);
    }

    @GetMapping(path = "/ndjson", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<Response> ndjson() {
        return Flux.fromStream(Stream.generate(() -> new Response(LocalTime.now())))
                   .delayElements(Duration.ofMillis(100))
                   .take(10);
    }

    @Data
    @AllArgsConstructor
    public static class Response {
        private LocalTime time;
    }
}
