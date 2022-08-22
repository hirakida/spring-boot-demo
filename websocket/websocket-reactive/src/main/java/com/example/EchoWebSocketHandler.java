package com.example;

import java.time.LocalTime;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class EchoWebSocketHandler implements WebSocketHandler {
    @Override
    public Mono<Void> handle(WebSocketSession session) {
        Flux<WebSocketMessage> messages =
                session.receive()
                       .map(WebSocketMessage::getPayloadAsText)
                       .flatMap(EchoWebSocketHandler::createResponse)
                       .map(session::textMessage);
        return session.send(messages);
    }

    private static Flux<String> createResponse(String payload) {
        return Flux.just(payload + '@' + LocalTime.now());
    }
}
