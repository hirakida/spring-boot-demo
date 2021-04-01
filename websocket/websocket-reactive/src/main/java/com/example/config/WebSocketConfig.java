package com.example.config;

import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;

import com.example.EchoWebSocketHandler;

@Configuration
public class WebSocketConfig {
    @Bean
    public HandlerMapping handlerMapping(EchoWebSocketHandler webSocketHandler) {
        return new SimpleUrlHandlerMapping(Map.of("/ws/echo", webSocketHandler), -1);
    }
}
