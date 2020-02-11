package com.example.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeoutException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.WebAsyncTask;

import com.example.Result;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CallableController {
    private static final long TIMEOUT = 5000;
    private final CallableService callableService;

    @GetMapping("/callable/1")
    public Callable<Result> callable() {
        return callableService.callable();
    }

    @GetMapping("/callable/2")
    public WebAsyncTask<Result> webAsyncTask() {
        Callable<Result> callable = callableService.callable();
        return new WebAsyncTask<>(TIMEOUT, callable);
    }

    @ExceptionHandler(TimeoutException.class)
    public ResponseEntity<Object> handleTimeoutException(TimeoutException e) {
        log.error("{}", e.getMessage(), e);
        return new ResponseEntity<>(null, null, HttpStatus.SERVICE_UNAVAILABLE);
    }
}
