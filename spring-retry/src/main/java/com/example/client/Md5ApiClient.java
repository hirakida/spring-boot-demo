package com.example.client;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.model.Md5;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class Md5ApiClient {

    private static final String URL = "http://md5.jsontest.com/";
    private final RestOperations restOperations = new RestTemplateBuilder().build();
    private final RetryTemplate retryTemplate;

    public Md5 getMd5(String text) {
        return retryTemplate.execute(
                context -> {
                    log.info("retryCallback md5 start {}", context);
                    Md5 response = restOperations.getForObject(buildUrl(text), Md5.class);
                    log.info("retryCallback md5 end {}", response);
                    return response;
                },
                context -> {
                    log.info("recoveryCallback md5 {}", context);
                    return new Md5();
                });
    }

    private static String buildUrl(String text) {
        return UriComponentsBuilder.fromHttpUrl(URL)
                                   .queryParam("text", text)
                                   .toUriString();
    }
}
