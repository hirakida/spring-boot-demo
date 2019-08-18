package com.example.client;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.client.model.DateTime;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DateApiClient {
    private static final String URL = "http://date.jsontest.com/";
    private final RestTemplate restTemplate;

    public DateApiClient(RestTemplateBuilder builder) {
        restTemplate = builder.build();
    }

    @Retryable(exceptionExpression = "#{@exceptionChecker.shouldRetry(#root)}",
            maxAttemptsExpression = "#{${retry.max-attempt}}",
            backoff = @Backoff(delayExpression = "#{${retry.delay}}"))
    public DateTime getDate() {
        log.info("@Retryable date start");
        DateTime response = restTemplate.getForObject(URL, DateTime.class);
        log.info("@Retryable date end {}", response);
        return response;
    }

    @Recover
    public DateTime dateRecover() {
        log.info("@Recover date");
        return new DateTime();
    }
}
