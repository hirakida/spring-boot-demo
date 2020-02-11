package com.example.future;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import com.example.Result;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FutureService {

    @Async
    public ListenableFuture<Result> listenableFuture() {
        ListenableFuture<Result> future = AsyncResult.forValue(createResult());
        future.addCallback(result -> log.info("success: {}", result),
                           e -> log.error("{}", e.getMessage(), e));
        return future;
    }

    @Async
    public CompletableFuture<Result> completableFuture() {
        return CompletableFuture.completedFuture(createResult())
                                .whenComplete((result, e) -> {
                                    if (e != null) {
                                        log.error("{}", e.getMessage(), e);
                                    } else {
                                        log.info("success: {}", result);
                                    }
                                });
    }

    private static Result createResult() {
        LocalDateTime start = LocalDateTime.now();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            log.error("{}", e.getMessage(), e);
        }
        Result result = new Result(start, LocalDateTime.now());
        log.info("result={}", result);
        return result;
    }
}
