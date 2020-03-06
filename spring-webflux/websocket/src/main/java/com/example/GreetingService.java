package com.example;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.example.model.GreetingRequest;
import com.example.model.GreetingResponse;

import reactor.core.publisher.Flux;

@Service
public class GreetingService {

    public Flux<GreetingResponse> greet(GreetingRequest request) {
        return Flux.fromStream(Stream.generate(() -> new GreetingResponse(buildMessage(request))))
                   .delayElements(Duration.ofSeconds(1));
    }

    private static String buildMessage(GreetingRequest request) {
        return String.format("%s @ %s", request.getName(), Instant.now());
    }
}
