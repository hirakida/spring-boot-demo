package com.example;

import java.time.LocalTime;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.rsocket.annotation.ConnectMapping;
import org.springframework.stereotype.Controller;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Controller
@Slf4j
public class RSocketController {
    @ConnectMapping
    public Mono<Void> connect() {
        log.info("connect");
        return Mono.empty();
    }

    @MessageMapping("request_response")
    public Mono<HelloResponse> hello(HelloRequest request) {
        return Mono.just(new HelloResponse(String.format("Hello %s!", request.getName()),
                                           LocalTime.now()));
    }
}
