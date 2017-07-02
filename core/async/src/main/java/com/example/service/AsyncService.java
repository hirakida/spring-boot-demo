package com.example.service;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.context.request.async.DeferredResult;

import com.example.controller.AsyncApiController.Result;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AsyncService {

    // Asyncを付けると別スレッドで実行される
    @Async
    public void async(long millis) {
        log.info("Service start");
        run("async", millis);
        log.info("Service end");
    }

    @Async
    public void async(DeferredResult<Result> deferredResult) {
        log.info("Service start");
        Result result = run("DeferredResult", 3000L);
        deferredResult.setResult(result);
        log.info("Service end");
    }

    @Async
    public ListenableFuture<Result> listenableFuture() {
        log.info("Service start");
        Result result = run("ListenableFuture", 3000L);
        log.info("Service end");
        return new AsyncResult<>(result);
    }

    @Async
    public Future<Result> future(long millis) {
        log.info("Service start");
        Result result = run("future", millis);
        log.info("Service end");
        return new AsyncResult<>(result);
    }

    @Async
    public CompletableFuture<Result> completableFuture(long millis) {
        log.info("Service start");
        Result result = run("CompletableFuture", millis);
        log.info("Service end");
        return CompletableFuture.completedFuture(result);
    }

    private static Result run(String message, long millis) {
        if (millis == 0) {
            throw new IllegalArgumentException();
        }

        Result result = Result.of(message, LocalDateTime.now());
        result.setStartAt(LocalDateTime.now());
        try {
            Thread.sleep(millis);
            result.setEndAt(LocalDateTime.now());
        } catch (InterruptedException e) {
            log.error("error:", e);
        }
        return result;
    }
}
