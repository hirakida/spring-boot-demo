package com.example;

import java.net.URI;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;

@Configuration
public class RSocketConfig {

    @Bean
    public RSocketRequester rSocketTcpRequester(RSocketRequester.Builder builder) {
        return builder.connectTcp("localhost", 7000)
                      .block();
    }

    @Bean
    public RSocketRequester rSocketWebsocketRequester(RSocketRequester.Builder builder) {
        return builder.connectWebSocket(URI.create("ws://localhost:8081/rsocket"))
                      .block();
    }
}
