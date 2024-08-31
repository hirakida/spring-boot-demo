package com.example;

import java.time.LocalTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.rsocket.annotation.ConnectMapping;
import org.springframework.stereotype.Controller;

import reactor.core.publisher.Mono;

@Controller
public class RSocketController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RSocketController.class);

    @ConnectMapping
    public Mono<Void> connect() {
        LOGGER.info("connect");
        return Mono.empty();
    }

    @MessageMapping("request_response")
    public Mono<HelloResponse> hello(HelloRequest request) {
        final HelloResponse response = new HelloResponse("Hello, %s!".formatted(request.name()),
                                                         LocalTime.now());
        return Mono.just(response);
    }

    public record HelloRequest(String name) {}

    public record HelloResponse(String message, LocalTime time) {}
}
