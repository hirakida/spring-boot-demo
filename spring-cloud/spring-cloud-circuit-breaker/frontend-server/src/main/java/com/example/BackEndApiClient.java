package com.example;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class BackendApiClient {
    private final RestTemplate restTemplate;
    private final CircuitBreaker circuitBreaker;
    private final ObjectMapper objectMapper;

    public JsonNode hello() {
        return circuitBreaker.run(() -> restTemplate.getForObject("http://localhost:8081/hello",
                                                                  JsonNode.class),
                                  this::fallback);
    }

    private JsonNode fallback(Throwable t) {
        log.warn("fallback: {}", t.getMessage());
        return objectMapper.valueToTree(Map.of("fallback", LocalDateTime.now()));
    }
}
