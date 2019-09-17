package com.example;

import java.time.ZoneId;

import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ApiController {
    private final RSocketRequester rSocketRequester;

    @GetMapping("/datetime")
    public Publisher<JsonNode> datetime() {
        return rSocketRequester.route("datetime")
                               .data(new DateTimeRequest(ZoneId.systemDefault()))
                               .retrieveMono(JsonNode.class);
    }

    @GetMapping(path = "/time", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Publisher<JsonNode> time() {
        return rSocketRequester.route("time")
                               .data(new DateTimeRequest(ZoneId.systemDefault()))
                               .retrieveFlux(JsonNode.class);
    }

    @GetMapping(path = "/random", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Publisher<Integer> random(@RequestParam(defaultValue = "0") long seed) {
        return rSocketRequester.route("random")
                               .data(new RandomRequest(seed))
                               .retrieveFlux(Integer.class);
    }
}
