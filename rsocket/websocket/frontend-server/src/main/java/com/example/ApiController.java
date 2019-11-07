package com.example;

import java.time.ZoneId;

import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ApiController {
    private final RSocketRequester rSocketRequester;

    @GetMapping(path = "/datetime", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Publisher<DateTimeResponse> datetime() {
        return rSocketRequester.route("datetime")
                               .data(new DateTimeRequest(ZoneId.systemDefault()))
                               .retrieveFlux(DateTimeResponse.class);
    }
}
