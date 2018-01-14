package com.example;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.model.DateTime;
import com.example.model.IpAddr;
import com.example.model.Md5;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class JsonTestApiClient {

    private final RestOperations restOperations = new RestTemplateBuilder().build();
    private final RetryTemplate retryTemplate;

    @Retryable(value = ResourceAccessException.class, maxAttempts = 4, backoff = @Backoff(delay = 500))
    public IpAddr getIpAddr() {
        log.info("@Retryable ip start");
        IpAddr response = restOperations.getForObject("http://ip.jsontest.com/", IpAddr.class);
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
        DateTime response = restOperations.getForObject("http://date.jsontest.com/", DateTime.class);
        log.info("@Retryable dateTime end, response={}", response);
        return response;
    }

    @Recover
    public DateTime dateTimeRecover() {
        log.info("@Recover dateTime");
        return new DateTime();
    }

    /**
     * RetryTemplate
     */
    public Md5 getMd5(String text) {
        return retryTemplate.execute(
                context -> {
                    log.info("RetryCallback.doWithRetry md5 start {}", context);
                    String url = UriComponentsBuilder.fromHttpUrl("http://md5.jsontest.com/")
                                                     .queryParam("text", text)
                                                     .toUriString();
                    Md5 response = restOperations.getForObject(url, Md5.class);
                    log.info("RetryCallback.doWithRetry md5 end response={}", response);
                    return response;
                },
                context -> {
                    log.info("RecoveryCallback.recover md5 {}", context);
                    return new Md5();
                });
    }
}
