package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class RandomController {
    private final RandomService randomService;

    @GetMapping("/random")
    public Mono<Random> random() {
        return randomService.getRandom()
                            .map(Random::new);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Random {
        private long random;
    }
}
