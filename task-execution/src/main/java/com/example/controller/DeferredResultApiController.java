package com.example.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.context.request.async.DeferredResult;

import com.example.model.ResultData;
import com.example.service.DeferredResultService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class DeferredResultApiController {

    private static final long TIMEOUT = 5000;
    private final DeferredResultService deferredResultService;

    @GetMapping("/deferred_result")
    public DeferredResult<ResultData> deferredResult() {
        log.info("DeferredResult start");
        DeferredResult<ResultData> deferredResult = new DeferredResult<>(TIMEOUT);
        deferredResultService.deferredResult(deferredResult);
        log.info("DeferredResult end");
        return deferredResult;
    }

    @ExceptionHandler(AsyncRequestTimeoutException.class)
    public ResponseEntity<Object> handleAsyncRequestTimeoutException(AsyncRequestTimeoutException e) {
        ResultData result = new ResultData("timeout", LocalDateTime.now(), LocalDateTime.now());
        return new ResponseEntity<>(result, null, HttpStatus.SERVICE_UNAVAILABLE);
    }
}
