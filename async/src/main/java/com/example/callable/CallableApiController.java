package com.example.callable;

import java.time.LocalDateTime;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.WebAsyncTask;

import com.example.Result;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CallableApiController {
    private static final long TIMEOUT = 5000;

    @GetMapping("/callable/1")
    public Callable<Result> callable() {
        log.info("Callable start");
        Callable<Result> callable = createCallable();
        log.info("Callable end");
        return callable;
    }

    @GetMapping("/callable/2")
    public WebAsyncTask<Result> webAsyncTask() {
        log.info("Callable start");
        Callable<Result> callable = createCallable();
        WebAsyncTask<Result> task = new WebAsyncTask<>(TIMEOUT, callable);
        log.info("Callable end");
        return task;
    }

    private static Callable<Result> createCallable() {
        return () -> {
            LocalDateTime start = LocalDateTime.now();
            TimeUnit.SECONDS.sleep(3);
            Result result = new Result(start, LocalDateTime.now());
            log.info("{}", result);
            return result;
        };
    }

    @ExceptionHandler(TimeoutException.class)
    public ResponseEntity<Object> handleTimeoutException(TimeoutException e) {
        log.error("{}", e.getMessage(), e);
        return new ResponseEntity<>(null, null, HttpStatus.SERVICE_UNAVAILABLE);
    }
}
