package com.example;

import java.time.LocalDateTime;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncTask;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AsyncController {
    private static final long TIMEOUT = 5000;
    private final DeferredResultService service;

    @GetMapping("/deferred_result")
    public DeferredResult<Response> deferredResult() {
        DeferredResult<Response> result = new DeferredResult<>(TIMEOUT);
        service.deferredResult(result);
        return result;
    }

    @GetMapping("/callable/1")
    public Callable<Response> callable() {
        return call();
    }

    @GetMapping("/callable/2")
    public WebAsyncTask<Response> webAsyncTask() {
        return new WebAsyncTask<>(TIMEOUT, call());
    }

    private static Callable<Response> call() {
        return () -> {
            LocalDateTime start = LocalDateTime.now();
            TimeUnit.SECONDS.sleep(3);
            LocalDateTime end = LocalDateTime.now();
            Response response = new Response(start, end);
            log.info("result={}", response);
            return response;
        };
    }

    @ExceptionHandler(AsyncRequestTimeoutException.class)
    public ResponseEntity<Object> handleAsyncRequestTimeoutException(AsyncRequestTimeoutException e) {
        log.error("{}", e.getMessage(), e);
        return new ResponseEntity<>(null, null, HttpStatus.SERVICE_UNAVAILABLE);
    }
}
