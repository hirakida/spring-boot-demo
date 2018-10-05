package com.example.service;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import com.example.model.ResultData;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FutureService {

    @Async
    public Future<ResultData> future() {
        log.info("Service start");
        ResultData result = process("Future");
        log.info("Service end");
        return new AsyncResult<>(result);
    }

    @Async
    public ListenableFuture<ResultData> listenableFuture() {
        log.info("Service start");
        ResultData result = process("ListenableFuture");
        log.info("Service end");
        return new AsyncResult<>(result);
    }

    @Async
    public CompletableFuture<ResultData> completableFuture() {
        log.info("Service start");
        ResultData result = process("CompletableFuture");
        log.info("Service end");
        return CompletableFuture.completedFuture(result);
    }

    private static ResultData process(String message) {
        ResultData result = new ResultData(message, LocalDateTime.now());
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            log.error("{}", e.getMessage(), e);
        }
        result.setEndAt(LocalDateTime.now());
        return result;
    }
}
