package com.example;

import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;

import reactor.core.publisher.Flux;

@Configuration
public class WebSocketConfig {

    @Bean
    public WebSocketHandler webSocketHandler(GreetingService greetingService) {
        return session -> {
            Flux<WebSocketMessage> messages = session.receive()
                                                     .map(WebSocketMessage::getPayloadAsText)
                                                     .map(GreetingRequest::new)
                                                     .flatMap(greetingService::greet)
                                                     .map(GreetingResponse::getMessage)
                                                     .map(session::textMessage);
            return session.send(messages);
        };
    }

    @Bean
    public SimpleUrlHandlerMapping simpleUrlHandlerMapping(WebSocketHandler webSocketHandler) {
        return new SimpleUrlHandlerMapping(Map.of("/ws/greetings", webSocketHandler), 1);
    }

    @Bean
    public WebSocketHandlerAdapter webSocketHandlerAdapter() {
        return new WebSocketHandlerAdapter();
    }
}
