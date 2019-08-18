package com.example.client;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.client.model.Md5;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Md5ApiClient {
    private static final String URL = "http://md5.jsontest.com/";
    private final RestTemplate restTemplate;
    private final RetryTemplate retryTemplate;

    public Md5ApiClient(RestTemplateBuilder builder, RetryTemplate retryTemplate) {
        restTemplate = builder.build();
        this.retryTemplate = retryTemplate;
    }

    public Md5 getMd5(String text) {
        String url = UriComponentsBuilder.fromHttpUrl(URL)
                                         .queryParam("text", text)
                                         .toUriString();

        return retryTemplate.execute(
                context -> {
                    log.info("retryCallback md5 start {}", context);
                    Md5 response = restTemplate.getForObject(url, Md5.class);
                    log.info("retryCallback md5 end {}", response);
                    return response;
                },
                context -> {
                    log.info("recoveryCallback md5 {}", context);
                    return new Md5();
                });
    }
}
