package com.example.service;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.context.request.async.DeferredResult;

import com.example.ResultData;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DeferredService {

    private static final long SLEEP_SEC = 3;

    /**
     * DeferredResult
     */
    @Async
    public void deferredResult(DeferredResult<ResultData> deferredResult) {
        log.info("Service start");
        ResultData result = process("DeferredResult");
        deferredResult.setResult(result);
        log.info("Service end");
    }

    /**
     * ListenableFuture
     */
    @Async
    public ListenableFuture<ResultData> listenableFuture() {
        log.info("Service start");
        ResultData result = process("ListenableFuture");
        log.info("Service end");
        return new AsyncResult<>(result);
    }

    /**
     * CompletableFuture
     */
    @Async
    public CompletableFuture<ResultData> completableFuture() {
        log.info("Service start");
        ResultData result = process("CompletableFuture");
        log.info("Service end");
        return CompletableFuture.completedFuture(result);
    }

    private static ResultData process(String message) {
        ResultData result = ResultData.of(message, LocalDateTime.now());
        try {
            TimeUnit.SECONDS.sleep(SLEEP_SEC);
        } catch (InterruptedException e) {
            log.error("error:", e);
        }
        result.setEndAt(LocalDateTime.now());
        return result;
    }
}
