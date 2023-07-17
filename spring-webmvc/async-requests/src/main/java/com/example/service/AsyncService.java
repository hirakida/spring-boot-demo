package com.example.service;

import java.time.LocalDateTime;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

import com.example.controller.Response;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AsyncService {
    @Async
    public CompletableFuture<Response> completableFuture() {
        log.info("future");
        return CompletableFuture.completedFuture(execute())
                                .whenComplete((result, e) -> {
                                    if (e != null) {
                                        log.error("{}", e.getMessage(), e);
                                    }
                                });
    }

    @Async
    public void deferredResult(DeferredResult<Response> deferredResult) {
        log.info("deferredResult");
        deferredResult.setResult(execute());
    }

    public Callable<Response> callable() {
        log.info("callable");
        return AsyncService::execute;
    }

    private static Response execute() {
        LocalDateTime start = LocalDateTime.now();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            log.error("{}", e.getMessage(), e);
        }
        LocalDateTime end = LocalDateTime.now();
        return new Response(start, end);
    }
}
