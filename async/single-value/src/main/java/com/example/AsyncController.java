package com.example;

import java.time.LocalDateTime;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncTask;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AsyncController {
    private static final long TIMEOUT = 5000;
    private final AsyncService asyncService;

    @GetMapping("/callable/1")
    public Callable<AsyncResponse> callable() {
        return call();
    }

    @GetMapping("/callable/2")
    public WebAsyncTask<AsyncResponse> webAsyncTask() {
        return new WebAsyncTask<>(TIMEOUT, call());
    }

    @GetMapping("/deferred_result")
    public DeferredResult<AsyncResponse> deferredResult() {
        DeferredResult<AsyncResponse> deferredResult = new DeferredResult<>(TIMEOUT);
        asyncService.deferredResult(deferredResult);
        return deferredResult;
    }

    @GetMapping("/future/1")
    public ListenableFuture<AsyncResponse> listenableFuture() {
        return asyncService.listenableFuture();
    }

    @GetMapping("/future/2")
    public CompletableFuture<AsyncResponse> completableFuture() {
        return asyncService.completableFuture();
    }

    private static Callable<AsyncResponse> call() {
        return () -> {
            LocalDateTime start = LocalDateTime.now();
            TimeUnit.SECONDS.sleep(3);
            AsyncResponse response = new AsyncResponse(start, LocalDateTime.now());
            log.info("result={}", response);
            return response;
        };
    }
}
