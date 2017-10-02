package com.example.service;

import java.time.LocalDateTime;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.example.ResultData;

import lombok.extern.slf4j.Slf4j;

/**
 * Spring MVC管理下のthreadで実行される
 */
@Service
@Slf4j
public class CallableService {

    private static final long SLEEP_SEC = 3;

    public Callable<ResultData> callable() {
        return () -> {
            log.info("Service start");
            ResultData result = ResultData.of("Callable", LocalDateTime.now());
            TimeUnit.SECONDS.sleep(SLEEP_SEC);
            result.setEndAt(LocalDateTime.now());
            log.info("Service end");
            return result;
        };
    }
}
