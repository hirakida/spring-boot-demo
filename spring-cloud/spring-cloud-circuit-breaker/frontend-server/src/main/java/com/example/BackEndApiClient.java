package com.example;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class BackEndApiClient {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @HystrixCommand(fallbackMethod = "fallback",
            commandProperties = {
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2"),
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000")
            })
    public JsonNode getDateTime() {
        return restTemplate.getForObject("http://localhost:8081/datetime", JsonNode.class);
    }

    public JsonNode fallback() {
        log.warn("fallback");
        return objectMapper.valueToTree(Map.of("message", "fallback"));
    }
}
