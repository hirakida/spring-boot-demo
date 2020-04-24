package com.example;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.context.request.async.DeferredResult;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AsyncService {

    @Async
    public void deferredResult(DeferredResult<AsyncResponse> deferredResult) {
        AsyncResponse response = createResponse();
        deferredResult.setResult(response);
        log.info("result={}", response);
    }

    @Async
    public ListenableFuture<AsyncResponse> listenableFuture() {
        AsyncResponse response = createResponse();
        ListenableFuture<AsyncResponse> future = AsyncResult.forValue(response);
        future.addCallback(result -> log.info("success: {}", result),
                           e -> log.error("{}", e.getMessage(), e));
        return future;
    }

    @Async
    public CompletableFuture<AsyncResponse> completableFuture() {
        AsyncResponse response = createResponse();
        return CompletableFuture.completedFuture(response)
                                .whenComplete((result, e) -> {
                                    if (e != null) {
                                        log.error("{}", e.getMessage(), e);
                                    } else {
                                        log.info("success: {}", result);
                                    }
                                });
    }

    private static AsyncResponse createResponse() {
        LocalDateTime start = LocalDateTime.now();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            log.error("{}", e.getMessage(), e);
        }
        AsyncResponse response = new AsyncResponse(start, LocalDateTime.now());
        log.info("result={}", response);
        return response;
    }
}
