package com.example;

import java.net.URI;

import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/websocket")
public class WebsocketController {
    private static final String DEFAULT_NAME = "hirakida";
    private final RSocketRequester rsocketRequester;

    public WebsocketController(RSocketRequester.Builder builder) {
        rsocketRequester = builder.websocket(URI.create("ws://localhost:8081/rsocket"));
    }

    @GetMapping("/request_response")
    public Mono<HelloResponse> websocket(@RequestParam(defaultValue = DEFAULT_NAME) String name) {
        return rsocketRequester.route("request_response")
                               .data(new HelloRequest(name))
                               .retrieveMono(HelloResponse.class);
    }
}
