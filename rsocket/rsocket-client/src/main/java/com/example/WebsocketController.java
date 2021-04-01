package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/websocket")
@RequiredArgsConstructor
public class WebsocketController {
    private static final String DEFAULT_NAME = "hirakida";
    private final WebsocketClient client;

    @GetMapping("/request_response")
    public Mono<HelloResponse> websocket(@RequestParam(defaultValue = DEFAULT_NAME) String name) {
        return client.requestResponse(new HelloRequest(name));
    }
}
