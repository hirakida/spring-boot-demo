package com.example;

import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class WebsocketController {
    private final RSocketRequester rSocketWebsocketRequester;

    @GetMapping("/websocket")
    public Mono<HelloResponse> websocket(@RequestParam(defaultValue = "hirakida") String name) {
        return rSocketWebsocketRequester.route("requestResponse")
                                        .data(new HelloRequest(name))
                                        .retrieveMono(HelloResponse.class);
    }
}
