package com.example;

import java.time.Duration;
import java.util.stream.Stream;

import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/tcp")
@RequiredArgsConstructor
public class TcpController {
    private final RSocketRequester rSocketTcpRequester;

    @GetMapping("/request_response")
    public Publisher<HelloResponse> requestResponse(@RequestParam(defaultValue = "hirakida") String name) {
        return rSocketTcpRequester.route("requestResponse")
                                  .data(new HelloRequest(name))
                                  .retrieveMono(HelloResponse.class);
    }

    @GetMapping("/fire_and_forget")
    public Publisher<Void> fireAndForget(@RequestParam(defaultValue = "hirakida") String name) {
        return rSocketTcpRequester.route("fireAndForget")
                                  .data(new HelloRequest(name))
                                  .send();
    }

    @GetMapping(path = "/request_stream", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Publisher<HelloResponse> requestStream(@RequestParam(defaultValue = "hirakida") String name) {
        return rSocketTcpRequester.route("requestStream")
                                  .data(new HelloRequest(name))
                                  .retrieveFlux(HelloResponse.class);
    }

    @GetMapping(path = "/request_channel", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Publisher<HelloResponse> requestChannel(@RequestParam(defaultValue = "hirakida") String name) {
        return rSocketTcpRequester.route("requestChannel")
                                  .data(Flux.fromStream(Stream.generate(() -> new HelloRequest(name)))
                                            .delayElements(Duration.ofSeconds(1)))
                                  .retrieveFlux(HelloResponse.class);
    }
}
