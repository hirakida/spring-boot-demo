package com.example.deferredresult;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

import com.example.Result;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DeferredResultService {

    @Async
    public void deferredResult(DeferredResult<Result> deferredResult) {
        LocalDateTime start = LocalDateTime.now();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            log.error("{}", e.getMessage(), e);
        }
        Result result = new Result(start, LocalDateTime.now());
        deferredResult.setResult(result);
        log.info("{}", result);
    }
}
