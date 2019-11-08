package com.example;

import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ApiController {
    private final RSocketRequester rSocketTcpRequester;
    private final RSocketRequester rSocketWebsocketRequester;

    @GetMapping("/hello")
    public Publisher<HelloResponse> hello(@RequestParam String name) {
        return rSocketWebsocketRequester.route("hello")
                                        .data(new HelloRequest(name))
                                        .retrieveMono(HelloResponse.class);
    }

    @GetMapping(path = "/datetime", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Publisher<DateTimeResponse> datetime() {
        return rSocketTcpRequester.route("datetime")
                                  .retrieveFlux(DateTimeResponse.class);
    }
}
