package com.example;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
public class RandomService {
    private final SecureRandom random;

    public RandomService() throws NoSuchAlgorithmException {
        random = SecureRandom.getInstance("NativePRNG");
    }

    public Mono<Long> getRandom() {
        return Mono.just(random.nextLong());
    }
}
