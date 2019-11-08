package com.example;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.stream.Stream;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.rsocket.annotation.ConnectMapping;
import org.springframework.stereotype.Controller;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@Slf4j
public class RSocketController {

    @ConnectMapping
    public Mono<Void> connect() {
        log.info("connect");
        return Mono.empty();
    }

    @MessageMapping("datetime")
    public Flux<DateTimeResponse> datetime() {
        return Flux.fromStream(Stream.generate(() -> new DateTimeResponse(LocalDateTime.now())))
                   .delayElements(Duration.ofSeconds(1));
    }
}
