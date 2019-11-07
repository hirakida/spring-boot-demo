package com.example;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.stream.Stream;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import reactor.core.publisher.Flux;

@Controller
public class RSocketController {

    @MessageMapping("datetime")
    public Flux<DateTimeResponse> datetime(DateTimeRequest request) {
        return Flux.fromStream(Stream.generate(() -> new DateTimeResponse(LocalDateTime.now(request.getZoneId()))))
                   .delayElements(Duration.ofSeconds(1));
    }
}
