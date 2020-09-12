package com.example.controller;

import java.time.LocalDateTime;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.WebAsyncTask;

import com.example.AsyncResponse;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CallableController {
    private static final long TIMEOUT = 5000;

    @GetMapping("/callable/1")
    public Callable<AsyncResponse> callable() {
        return call();
    }

    @GetMapping("/callable/2")
    public WebAsyncTask<AsyncResponse> webAsyncTask() {
        return new WebAsyncTask<>(TIMEOUT, call());
    }

    private static Callable<AsyncResponse> call() {
        return () -> {
            LocalDateTime start = LocalDateTime.now();
            TimeUnit.SECONDS.sleep(3);
            LocalDateTime end = LocalDateTime.now();
            AsyncResponse response = new AsyncResponse(start, end);
            log.info("result={}", response);
            return response;
        };
    }
}
