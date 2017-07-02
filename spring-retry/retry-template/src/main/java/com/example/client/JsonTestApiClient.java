package com.example.client;

import org.springframework.retry.RecoveryCallback;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;
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
    final RetryTemplate retryTemplate;

    public IpAddr getIp() {
        return retryTemplate.execute(new RetryCallback<IpAddr, RuntimeException>() {
            @Override
            public IpAddr doWithRetry(RetryContext context) {
                log.info("doWithRetry ip start {}", context);
                String url = UriComponentsBuilder.fromHttpUrl("http://ip.jsontest.com/")
                                                 .toUriString();
                IpAddr response = restOperations.getForObject(url, IpAddr.class);
                log.info("doWithRetry ip end response={}", response);
                return response;
            }
        }, new RecoveryCallback<IpAddr>() {
            @Override
            public IpAddr recover(RetryContext context) {
                log.info("recover ip {}", context);
                return new IpAddr("127.0.0.1");
            }
        });
    }

    public DateTime getDateTime() {
        return retryTemplate.execute(
                context -> {
                    log.info("doWithRetry date start {}", context);
                    String url = UriComponentsBuilder.fromHttpUrl("http://date.jsontest.com/")
                                                     .toUriString();
                    DateTime response = restOperations.getForObject(url, DateTime.class);
                    log.info("doWithRetry date end response={}", response);
                    return response;
                },
                context -> {
                    log.info("recover date {}", context);
                    return new DateTime();
                });
    }
}
