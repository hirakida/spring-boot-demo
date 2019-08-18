package com.example.client;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.example.client.model.IpAddr;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class IpApiClient {
    private static final String URL = "http://ip.jsontest.com/";
    private final RestTemplate restTemplate;

    public IpApiClient(RestTemplateBuilder builder) {
        restTemplate = builder.build();
    }

    @Retryable(value = ResourceAccessException.class, maxAttempts = 4, backoff = @Backoff(delay = 500))
    public IpAddr getIp() {
        log.info("@Retryable ip start");
        IpAddr response = restTemplate.getForObject(URL, IpAddr.class);
        log.info("@Retryable ip end {}", response);
        return response;
    }

    @Recover
    public IpAddr ipRecover() {
        log.info("@Recover ip");
        return new IpAddr("127.0.0.1");
    }
}
