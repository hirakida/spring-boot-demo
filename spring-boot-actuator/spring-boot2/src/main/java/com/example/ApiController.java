package com.example;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import io.micrometer.core.annotation.Timed;

@RestController
public class ApiController {

    private final RestTemplate restTemplate;

    public ApiController(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    @GetMapping("/metrics/hello")
    @Timed("hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/metrics/now")
    @Timed("now")
    public LocalDateTime now() {
        return LocalDateTime.now();
    }

    @GetMapping("/metrics/md5")
    @Timed("md5")
    public Map<?, ?> md5(@RequestParam String text) {
        String uri = UriComponentsBuilder.fromHttpUrl("http://md5.jsontest.com")
                                         .queryParam("text", "{text}")
                                         .toUriString();
        return restTemplate.getForObject(uri, Map.class, text);
    }
}
