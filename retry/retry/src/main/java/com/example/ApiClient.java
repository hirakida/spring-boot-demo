package com.example;

import org.springframework.retry.RecoveryCallback;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
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

    /**
     * value
     * - retry処理対象のExceptionを定義する
     * maxAttempts
     * - 最大実行回数（リトライ回数ではなくて、初回の実行を含めた回数）
     * - default値は3
     * backoff
     * - delayなどが設定できる
     */
    @Retryable(value = HttpServerErrorException.class,
               maxAttempts = 4,
               backoff = @Backoff(delay = 500))
    public String useRetryable(int code) {
        log.info("@Retryable start code={}", code);
        String response = restOperations.getForObject(getApiUrl(code), String.class);
        log.info("@Retryable end code={}, response={}", code, response);
        return response;
    }

    @Recover
    public String recover(int code) {
        log.info("@Retryable recover code={}", code);
        return "retry out";
    }

    /**
     * use RetryTemplate
     */
    public String useRetryTemplate1(int code) {

        return retryTemplate.execute(new RetryCallback<String, RuntimeException>() {
            @Override
            public String doWithRetry(RetryContext context) {
                log.info("retryTemplate start code={} {}", code, context);
                String response = restOperations.getForObject(getApiUrl(code), String.class);
                log.info("retryTemplate end code={}, response={}", code, response);
                return response;
            }
        }, new RecoveryCallback<String>() {
            @Override
            public String recover(RetryContext context) {
                log.info("1 recover {}", context);
                return "recover";
            }
        });
    }

    public String useRetryTemplate2(int code) {

        // ラムダ式
        return retryTemplate.execute(
                context -> {
                    log.info("retryTemplate start code={} {}", code, context);
                    String response = restOperations.getForObject(getApiUrl(code), String.class);
                    log.info("retryTemplate end code={}, response={}", code, response);
                    return response;
                },
                context -> {
                    log.info("retryTemplate recover {}", context);
                    return "recover";
                });
    }

    private static String getApiUrl(int code) {
        return UriComponentsBuilder.fromHttpUrl(API_URL)
                                   .buildAndExpand(code)
                                   .toUriString();
    }
}
