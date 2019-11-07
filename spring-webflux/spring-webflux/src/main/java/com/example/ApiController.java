package com.example;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;
import java.util.Random;
import java.util.stream.Stream;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ApiController {
    private static final Random random = new Random();

    @GetMapping("/date")
    public Mono<Map<String, LocalDate>> date() {
        return Mono.just(Map.of("date", LocalDate.now()));
    }

    @GetMapping(path = "/datetime", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Map<String, LocalDateTime>> datetimeWithStream() {
        return Flux.fromStream(Stream.generate(() -> Map.of("datetime", LocalDateTime.now())))
                   .delayElements(Duration.ofSeconds(1));
    }

    @GetMapping(path = "/time", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<LocalTime> random() {
        return Flux.fromStream(Stream.generate(LocalTime::now))
                   .delayElements(Duration.ofSeconds(1));
    }
}
