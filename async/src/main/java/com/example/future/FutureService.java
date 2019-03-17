package com.example.future;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
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
    public Future<Result> future() {
        return new AsyncResult<>(createResult());
    }

    @Async
    public ListenableFuture<Result> listenableFuture() {
        return new AsyncResult<>(createResult());
    }

    @Async
    public CompletableFuture<Result> completableFuture() {
        return CompletableFuture.completedFuture(createResult());
    }

    private static Result createResult() {
        LocalDateTime start = LocalDateTime.now();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            log.error("{}", e.getMessage(), e);
        }
        Result result = new Result(start, LocalDateTime.now());
        log.info("{}", result);
        return result;
    }
}
