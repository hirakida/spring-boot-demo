package com.example.client;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.model.DateTime;
import com.example.model.IpAddr;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class JsonTestApiClient {

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
    @Retryable(value = ResourceAccessException.class,
            maxAttempts = 4,
            backoff = @Backoff(delay = 500))
    public IpAddr getIpAddress() {
        log.info("@Retryable ip start");
        String url = UriComponentsBuilder.fromHttpUrl("http://ip.jsontest.com/")
                                         .toUriString();
        IpAddr response = restOperations.getForObject(url, IpAddr.class);
        log.info("@Retryable ip end response={}", response);
        return response;
    }

    @Recover
    public IpAddr ipRecover() {
        log.info("@Recover ip");
        return new IpAddr("127.0.0.1");
    }

    /**
     * exceptionExpression
     */
    @Retryable(exceptionExpression = "#{@retryExpressionHelper.shouldRetry(#root)}",
            maxAttemptsExpression = "#{${app.retry.max-attempt}}",
            backoff = @Backoff(delayExpression = "#{${app.retry.delay}}"))
    public DateTime getDateTime() {
        log.info("@Retryable dateTime start");
        String url = UriComponentsBuilder.fromHttpUrl("http://date.jsontest.com/")
                                         .toUriString();
        DateTime response = restOperations.getForObject(url, DateTime.class);
        log.info("@Retryable dateTime end, response={}", response);
        return response;
    }

    @Recover
    public DateTime dateTimeRecover() {
        log.info("@Recover dateTime");
        return new DateTime();
    }
}
