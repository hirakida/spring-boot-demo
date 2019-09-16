package com.example;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.stream.Stream;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class RSocketController {
    private static final Random random = new Random();

    @MessageMapping("datetime")
    public Mono<DateTimeResponse> datetime(DateTimeRequest request) {
        return Mono.just(new DateTimeResponse(LocalDateTime.now(request.getZoneId())));
    }

    @MessageMapping("random")
    public Flux<Integer> random(RandomRequest request) {
        random.setSeed(request.getSeed());
        return Flux.fromStream(Stream.generate(random::nextInt))
                   .delayElements(Duration.ofSeconds(1));
    }
}
