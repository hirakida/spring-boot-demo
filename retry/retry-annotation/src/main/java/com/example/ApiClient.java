package com.example;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
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
    public String retryable(int code) {
        log.info("@Retryable start code={}", code);
        String response = restOperations.getForObject(getApiUrl(code), String.class);
        log.info("@Retryable end code={}, response={}", code, response);
        return response;
    }

    @Recover
    public String recover(int code) {
        log.info("@Recover code={}", code);
        return "retry out";
    }

    /**
     * use exceptionExpression
     */
    @Retryable(exceptionExpression = "#{@retryExpressionHelper.shouldRetry(#root)}",
               maxAttemptsExpression = "#{${app.retry.max-attempt}}",
               backoff = @Backoff(delayExpression = "#{${app.retry.delay}}"))
    public String retryable2(int code) {
        log.info("@Retryable exceptionExpression start code={}", code);
        String response = restOperations.getForObject(getApiUrl(code), String.class);
        log.info("@Retryable exceptionExpression end code={}, response={}", code, response);
        return response;
    }

    private static String getApiUrl(int code) {
        return UriComponentsBuilder.fromHttpUrl(API_URL)
                                   .buildAndExpand(code)
                                   .toUriString();
    }
}
