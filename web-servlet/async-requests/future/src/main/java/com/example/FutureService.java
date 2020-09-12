package com.example;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FutureService {
    @Async
    public ListenableFuture<Response> listenableFuture() {
        Response response = execute();
        log.info("result={}", response);

        ListenableFuture<Response> future = AsyncResult.forValue(response);
        future.addCallback(result -> log.info("success: {}", result),
                           e -> log.error("{}", e.getMessage(), e));
        return future;
    }

    @Async
    public CompletableFuture<Response> completableFuture() {
        Response response = execute();
        log.info("result={}", response);

        return CompletableFuture.completedFuture(response)
                                .whenComplete((result, e) -> {
                                    if (e != null) {
                                        log.error("{}", e.getMessage(), e);
                                    } else {
                                        log.info("success: {}", result);
                                    }
                                });
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
