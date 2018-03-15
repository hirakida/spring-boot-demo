package com.example;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.context.request.async.DeferredResult;

import com.example.service.DeferredResultService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ApiController {

    private final DeferredResultService deferredResultService;

    @GetMapping
    public DeferredResult<ResultData> deferredResult(@RequestParam(defaultValue = "3") long sleep) {
        log.info("DeferredResult start");
        long timeout = 3000;
        DeferredResult<ResultData> deferredResult = new DeferredResult<>(timeout);
        deferredResultService.deferredResult(deferredResult, sleep);
        log.info("DeferredResult end");
        return deferredResult;
    }

    @ExceptionHandler(AsyncRequestTimeoutException.class)
    public ResponseEntity<Object> handleAsyncRequestTimeoutException(AsyncRequestTimeoutException e) {
        ResultData result = new ResultData("timeout", LocalDateTime.now(), LocalDateTime.now());
        return new ResponseEntity<>(result, null, HttpStatus.SERVICE_UNAVAILABLE);
    }
}
