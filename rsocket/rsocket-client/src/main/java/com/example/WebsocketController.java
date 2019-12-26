package com.example;

import org.reactivestreams.Publisher;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class WebsocketController {
    private final RSocketRequester rSocketWebsocketRequester;

    @GetMapping("/websocket")
    public Publisher<HelloResponse> websocket(@RequestParam(defaultValue = "hirakida") String name) {
        return rSocketWebsocketRequester.route("hello")
                                        .data(new HelloRequest(name))
                                        .retrieveMono(HelloResponse.class);
    }
}
