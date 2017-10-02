package com.example.service;

import java.time.LocalDateTime;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import com.example.ResultData;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AsyncService {

    private static final long SLEEP_SEC = 3;

    @Async
    public void async() {
        log.info("Service start");
        process("async");
        log.info("Service end");
    }

    /**
     * Future
     */
    @Async
    public Future<ResultData> future() {
        log.info("Service start");
        ResultData result = process("future");
        log.info("Service end");
        return new AsyncResult<>(result);
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
