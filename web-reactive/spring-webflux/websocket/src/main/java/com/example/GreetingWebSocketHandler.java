package com.example;

import java.time.Duration;
import java.time.LocalTime;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;

import lombok.AllArgsConstructor;
import lombok.Data;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class GreetingWebSocketHandler implements WebSocketHandler {
    @Override
    public Mono<Void> handle(WebSocketSession session) {
        Flux<WebSocketMessage> messages =
                session.receive()
                       .map(WebSocketMessage::getPayloadAsText)
                       .map(GreetingRequest::new)
                       .flatMap(GreetingWebSocketHandler::greet)
                       .map(GreetingResponse::getMessage)
                       .map(session::textMessage);
        return session.send(messages);
    }

    private static Flux<GreetingResponse> greet(GreetingRequest request) {
        return Flux.fromStream(Stream.generate(request::getName))
                   .delayElements(Duration.ofSeconds(1))
                   .map(name -> new GreetingResponse(name + '@' + LocalTime.now()));
    }

    @Data
    @AllArgsConstructor
    public static class GreetingRequest {
        private String name;
    }

    @Data
    @AllArgsConstructor
    public static class GreetingResponse {
        private String message;
    }
}
