package com.example;

import java.net.URI;

import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@Component
public class WebsocketClient {
    private final RSocketRequester rsocketRequester;

    public WebsocketClient(RSocketRequester.Builder builder) {
        rsocketRequester = builder.websocket(URI.create("ws://localhost:8081/rsocket"));
    }

    public Mono<HelloResponse> requestResponse(HelloRequest data) {
        return rsocketRequester.route("request_response")
                               .data(data)
                               .retrieveMono(HelloResponse.class);
    }
}
