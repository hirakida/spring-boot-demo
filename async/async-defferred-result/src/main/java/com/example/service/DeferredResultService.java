package com.example.service;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

import com.example.ResultData;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DeferredResultService {

    @Async
    public void deferredResult(DeferredResult<ResultData> deferredResult, long sleep) {
        log.info("Service start");
        ResultData result = new ResultData("DeferredResult", LocalDateTime.now());
        try {
            TimeUnit.SECONDS.sleep(sleep);
        } catch (InterruptedException e) {
            log.error("error:", e);
        }
        result.setEndAt(LocalDateTime.now());
        deferredResult.setResult(result);
        log.info("Service end");
    }
}
