package com.example.callable;

import java.time.LocalDateTime;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.example.Result;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CallableService {

    public Callable<Result> callable() {
        return () -> {
            LocalDateTime start = LocalDateTime.now();
            TimeUnit.SECONDS.sleep(3);
            Result result = new Result(start, LocalDateTime.now());
            log.info("result={}", result);
            return result;
        };
    }
}
