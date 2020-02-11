package com.example.deferredresult;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.context.request.async.DeferredResult;

import com.example.Result;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class DeferredResultController {
    private static final long TIMEOUT = 5000;
    private final DeferredResultService deferredResultService;

    @GetMapping("/deferred_result")
    public DeferredResult<Result> deferredResult() {
        DeferredResult<Result> deferredResult = new DeferredResult<>(TIMEOUT);
        deferredResultService.deferredResult(deferredResult);
        return deferredResult;
    }

    @ExceptionHandler(AsyncRequestTimeoutException.class)
    public ResponseEntity<Object> handleAsyncRequestTimeoutException(AsyncRequestTimeoutException e) {
        log.error("{}", e.getMessage(), e);
        return new ResponseEntity<>(null, null, HttpStatus.SERVICE_UNAVAILABLE);
    }
}
