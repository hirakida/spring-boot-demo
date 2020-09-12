package com.example.service;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

import com.example.AsyncResponse;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DeferredResultService {
    @Async
    public void deferredResult(DeferredResult<AsyncResponse> deferredResult) {
        LocalDateTime start = LocalDateTime.now();
        sleep(3);
        LocalDateTime end = LocalDateTime.now();

        AsyncResponse response = new AsyncResponse(start, end);
        log.info("result={}", response);
        deferredResult.setResult(response);
    }

    private static void sleep(long timeout) {
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            log.error("{}", e.getMessage(), e);
        }
    }
}
