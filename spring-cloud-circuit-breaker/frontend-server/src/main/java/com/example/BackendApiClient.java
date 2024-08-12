package com.example;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class BackendApiClient {
    private static final String URL = "http://localhost:18080/hello";
    private final RestTemplate restTemplate;
    private final CircuitBreaker circuitBreaker;
    private final ObjectMapper objectMapper;

    public BackendApiClient(RestTemplateBuilder builder,
                            CircuitBreaker circuitBreaker,
                            ObjectMapper objectMapper) {
        restTemplate = builder.build();
        this.circuitBreaker = circuitBreaker;
        this.objectMapper = objectMapper;
    }

    public JsonNode hello() {
        return circuitBreaker.run(() -> restTemplate.getForObject(URL, JsonNode.class),
                                  this::fallback);
    }

    private JsonNode fallback(Throwable t) {
        log.warn("fallback: {}", t.getMessage());
        return objectMapper.valueToTree(Map.of("fallback", LocalDateTime.now()));
    }
}
