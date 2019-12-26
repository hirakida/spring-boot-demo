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

    @MessageMapping("requestResponse")
    public Mono<HelloResponse> requestResponse(HelloRequest request) {
        return Mono.just(new HelloResponse(String.format("Hello %s!", request.getName()),
                                           LocalDateTime.now()));
    }

    @MessageMapping("fireAndForget")
    public Mono<Void> fireAndForget(HelloRequest request) {
        log.info("{}", request.getName());
        return Mono.empty();
    }

    @MessageMapping("requestStream")
    public Flux<HelloResponse> requestStream(HelloRequest request) {
        return Flux.fromStream(Stream.generate(() -> new HelloResponse(String.format("Hello %s!",
                                                                                     request.getName()),
                                                                       LocalDateTime.now())))
                   .delayElements(Duration.ofSeconds(1));
    }

    @MessageMapping("requestChannel")
    public Flux<HelloResponse> requestChannel(Flux<HelloRequest> requests) {
        return requests.delayElements(Duration.ofSeconds(1))
                       .map(request -> new HelloResponse(String.format("Hello %s!", request.getName()),
                                                         LocalDateTime.now()));
    }
}
