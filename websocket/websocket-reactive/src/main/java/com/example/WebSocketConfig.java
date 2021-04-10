package com.example;

import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;

@Configuration
public class WebSocketConfig {
    @Bean
    public HandlerMapping handlerMapping(EchoWebSocketHandler webSocketHandler) {
        return new SimpleUrlHandlerMapping(Map.of("/ws/echo", webSocketHandler), -1);
    }
}
