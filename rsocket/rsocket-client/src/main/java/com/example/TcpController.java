package com.example;

import java.time.Duration;
import java.util.stream.Stream;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/tcp")
@RequiredArgsConstructor
public class TcpController {
    private static final String DEFAULT_NAME = "hirakida";
    private final TcpClient client;

    @GetMapping("/request_response")
    public Mono<HelloResponse> requestResponse(@RequestParam(defaultValue = DEFAULT_NAME) String name) {
        return client.requestResponse(new HelloRequest(name));
    }

    @GetMapping("/fire_and_forget")
    public Mono<Void> fireAndForget(@RequestParam(defaultValue = DEFAULT_NAME) String name) {
        return client.fireAndForget(new HelloRequest(name));
    }

    @GetMapping(path = "/request_stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<HelloResponse> requestStream(@RequestParam(defaultValue = DEFAULT_NAME) String name) {
        return client.requestStream(new HelloRequest(name))
                     .delayElements(Duration.ofSeconds(1));
    }

    @GetMapping(path = "/request_channel", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<HelloResponse> requestChannel(@RequestParam(defaultValue = DEFAULT_NAME) String name) {
        Flux<HelloRequest> data = Flux.fromStream(Stream.generate(() -> new HelloRequest(name)))
                                      .delayElements(Duration.ofMillis(1000))
                                      .take(10);
        return client.requestChannel(data);
    }
}
