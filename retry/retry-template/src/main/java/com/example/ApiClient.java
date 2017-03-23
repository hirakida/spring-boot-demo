package com.example;

import org.springframework.retry.RecoveryCallback;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class ApiClient {

    private static final String API_URL = "http://localhost:8080/api/{code}";
    final RestOperations restOperations;
    final RetryTemplate retryTemplate;

    public String retryable(int code) {
        return retryTemplate.execute(new RetryCallback<String, RuntimeException>() {
            @Override
            public String doWithRetry(RetryContext context) {
                log.info("doWithRetry start code={} {}", code, context);
                String response = restOperations.getForObject(getApiUrl(code), String.class);
                log.info("doWithRetry end code={} response={}", code, response);
                return response;
            }
        }, new RecoveryCallback<String>() {
            @Override
            public String recover(RetryContext context) {
                log.info("recover {}", context);
                return "recover";
            }
        });
    }

    public String retryable2(int code) {
        return retryTemplate.execute(
                context -> {
                    log.info("doWithRetry start code={} {}", code, context);
                    String response = restOperations.getForObject(getApiUrl(code), String.class);
                    log.info("doWithRetry end code={} response={}", code, response);
                    return response;
                },
                context -> {
                    log.info("recover {}", context);
                    return "recover";
                });
    }

    private static String getApiUrl(int code) {
        return UriComponentsBuilder.fromHttpUrl(API_URL)
                                   .buildAndExpand(code)
                                   .toUriString();
    }
}
