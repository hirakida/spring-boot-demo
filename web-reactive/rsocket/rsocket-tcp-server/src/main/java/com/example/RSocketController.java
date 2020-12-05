package com.example;

import java.time.LocalTime;
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

    @MessageMapping("request_response")
    public Mono<HelloResponse> requestResponse(HelloRequest request) {
        return Mono.just(createResponse(request.getName()));
    }

    @MessageMapping("fire_and_forget")
    public Mono<Void> fireAndForget(HelloRequest request) {
        log.info("{}", request.getName());
        return Mono.empty();
    }

    @MessageMapping("request_stream")
    public Flux<HelloResponse> requestStream(HelloRequest request) {
        return Flux.fromStream(Stream.generate(request::getName))
                   .map(RSocketController::createResponse)
                   .take(10);
    }

    @MessageMapping("request_channel")
    public Flux<HelloResponse> requestChannel(Flux<HelloRequest> requests) {
        return Flux.from(requests)
                   .map(request -> createResponse(request.getName()));
    }

    private static HelloResponse createResponse(String name) {
        return new HelloResponse(String.format("Hello %s!", name), LocalTime.now());
    }
}
