package com.example.controller;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncTask;

import com.example.service.AsyncService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AsyncController {
    private static final long TIMEOUT = 5000;
    private final AsyncService service;

    @GetMapping("/future")
    public CompletableFuture<Response> completableFuture() {
        return service.completableFuture();
    }

    @GetMapping("/deferred_result")
    public DeferredResult<Response> deferredResult() {
        DeferredResult<Response> result = new DeferredResult<>(TIMEOUT);
        service.deferredResult(result);
        return result;
    }

    @GetMapping("/callable/1")
    public Callable<Response> callable() {
        return service.callable();
    }

    @GetMapping("/callable/2")
    public WebAsyncTask<Response> webAsyncTask() {
        Callable<Response> response = service.callable();
        return new WebAsyncTask<>(TIMEOUT, response);
    }

    @ExceptionHandler(AsyncRequestTimeoutException.class)
    public ResponseEntity<Object> handleAsyncRequestTimeoutException(AsyncRequestTimeoutException e) {
        log.error("{}", e.getMessage(), e);
        return new ResponseEntity<>(null, null, HttpStatus.SERVICE_UNAVAILABLE);
    }
}
