package com.example;

import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class TcpClient {
    private final RSocketRequester requester;

    public TcpClient(RSocketRequester.Builder builder) {
        requester = builder.tcp("localhost", 7000);
    }

    public Mono<HelloResponse> requestResponse(HelloRequest data) {
        return requester.route("request_response")
                        .data(data)
                        .retrieveMono(HelloResponse.class);
    }

    public Mono<Void> fireAndForget(HelloRequest data) {
        return requester.route("fire_and_forget")
                        .data(data)
                        .send();
    }

    public Flux<HelloResponse> requestStream(HelloRequest data) {
        return requester.route("request_stream")
                        .data(data)
                        .retrieveFlux(HelloResponse.class);
    }

    public Flux<HelloResponse> requestChannel(Flux<HelloRequest> data) {
        return requester.route("request_channel")
                        .data(data)
                        .retrieveFlux(HelloResponse.class);
    }
}
