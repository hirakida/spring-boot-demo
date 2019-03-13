package com.example.controller;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import io.micrometer.core.annotation.Timed;

@RestController
@RequestMapping("/metrics")
public class MetricsApiController {
    private final RestTemplate restTemplate;
    private final MessageDigest md5;

    public MetricsApiController(RestTemplateBuilder restTemplateBuilder) throws NoSuchAlgorithmException {
        restTemplate = restTemplateBuilder.build();
        md5 = MessageDigest.getInstance("MD5");
    }

    @GetMapping("/now")
    @Timed("now")
    public LocalDateTime now() {
        return LocalDateTime.now();
    }

    @GetMapping("/md5")
    @Timed("md5")
    public String md5(@RequestParam String text) {
        byte[] bytes = md5.digest(text.getBytes(StandardCharsets.UTF_8));
        return DigestUtils.md5DigestAsHex(bytes);
    }

    @GetMapping("/weather")
    @Timed("weather")
    public Map<?, ?> weather() {
        String cityCode = "130010";
        String uri = UriComponentsBuilder.fromHttpUrl("http://weather.livedoor.com/forecast/webservice/json/v1")
                                         .queryParam("city", "{city}")
                                         .build(false)
                                         .toUriString();
        return restTemplate.getForObject(uri, Map.class, cityCode);
    }
}
