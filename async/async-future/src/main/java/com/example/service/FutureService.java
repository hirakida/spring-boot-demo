package com.example.service;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import com.example.ResultData;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FutureService {

    @Async
    public Future<ResultData> future(long sleep) {
        log.info("Service start");
        ResultData result = process("Future", sleep);
        log.info("Service end");
        return new AsyncResult<>(result);
    }

    @Async
    public ListenableFuture<ResultData> listenableFuture(long sleep) {
        log.info("Service start");
        ResultData result = process("ListenableFuture", sleep);
        log.info("Service end");
        return new AsyncResult<>(result);
    }

    @Async
    public CompletableFuture<ResultData> completableFuture(long sleep) {
        log.info("Service start");
        ResultData result = process("CompletableFuture", sleep);
        log.info("Service end");
        return CompletableFuture.completedFuture(result);
    }

    private static ResultData process(String message, long sleep) {
        ResultData result = new ResultData(message, LocalDateTime.now());
        try {
            TimeUnit.SECONDS.sleep(sleep);
        } catch (InterruptedException e) {
            log.error("error:", e);
        }
        result.setEndAt(LocalDateTime.now());
        return result;
    }
}
