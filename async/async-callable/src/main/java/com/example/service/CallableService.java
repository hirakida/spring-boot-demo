package com.example.service;

import java.time.LocalDateTime;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.example.ResultData;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CallableService {

    public Callable<ResultData> callable(long sleep) {
        return () -> {
            log.info("Service start");
            ResultData result = new ResultData("Callable", LocalDateTime.now());
            TimeUnit.SECONDS.sleep(sleep);
            result.setEndAt(LocalDateTime.now());
            log.info("Service end");
            return result;
        };
    }
}
