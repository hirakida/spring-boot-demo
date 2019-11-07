package com.example;

import java.net.URI;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.messaging.rsocket.RSocketRequester;

@Configuration
public class RSocketConfig {

    @Bean
    public RSocketRequester rSocketRequester(RSocketRequester.Builder builder) {
        return builder.dataMimeType(MediaType.APPLICATION_STREAM_JSON)
                      .connectWebSocket(URI.create("ws://localhost:7000/rsocket"))
                      .block();
    }
}
