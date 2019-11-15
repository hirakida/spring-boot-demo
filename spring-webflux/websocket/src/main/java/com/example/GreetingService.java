package com.example;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;

@Service
public class GreetingService {

    public Flux<GreetingResponse> greet(GreetingRequest request) {
        return Flux
                .fromStream(Stream.generate(() -> new GreetingResponse(
                        String.format("%s @ %s", request.getName(), Instant.now()))))
                .delayElements(Duration.ofSeconds(1));
    }
}
