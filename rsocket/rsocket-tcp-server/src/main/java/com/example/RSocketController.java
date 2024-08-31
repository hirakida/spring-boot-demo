package com.example;

import java.time.LocalTime;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.rsocket.annotation.ConnectMapping;
import org.springframework.stereotype.Controller;

import reactor.core.publisher.Flux;
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
    public Mono<HelloResponse> requestResponse(HelloRequest request) {
        return Mono.just(createResponse(request.name()));
    }

    @MessageMapping("fire_and_forget")
    public Mono<Void> fireAndForget(HelloRequest request) {
        LOGGER.info("{}", request.name());
        return Mono.empty();
    }

    @MessageMapping("request_stream")
    public Flux<HelloResponse> requestStream(HelloRequest request) {
        return Flux.fromStream(Stream.generate(request::name))
                   .map(RSocketController::createResponse)
                   .take(10);
    }

    @MessageMapping("request_channel")
    public Flux<HelloResponse> requestChannel(Flux<HelloRequest> requests) {
        return Flux.from(requests)
                   .map(request -> createResponse(request.name()));
    }

    private static HelloResponse createResponse(String name) {
        return new HelloResponse("Hello, %s!".formatted(name), LocalTime.now());
    }

    public record HelloRequest(String name) {}

    public record HelloResponse(String message, LocalTime time) {}
}
