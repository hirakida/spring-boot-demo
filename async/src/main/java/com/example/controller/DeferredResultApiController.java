package com.example.controller;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.context.request.async.DeferredResult;

import com.example.ResultData;
import com.example.service.DeferredService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/deferred")
@RequiredArgsConstructor
@Slf4j
public class DeferredResultApiController {

    private final DeferredService deferredService;

    /**
     * DeferredResult
     */
    @GetMapping("/1")
    public DeferredResult<ResultData> deferredResult() {
        log.info("DeferredResult start");
        // 引数にtimeoutを指定できる
        DeferredResult<ResultData> deferredResult = new DeferredResult<>();
        deferredService.deferredResult(deferredResult);
        log.info("DeferredResult end");
        return deferredResult;
    }

    /**
     * ListenableFuture
     */
    @GetMapping("/2")
    public ListenableFuture<ResultData> listenableFuture() {
        log.info("ListenableFuture start");
        ListenableFuture<ResultData> future = deferredService.listenableFuture();
        future.addCallback(result -> log.info("result: {}", result),
                           e -> log.error("error", e));
        log.info("ListenableFuture end");
        return future;
    }

    /**
     * CompletableFuture
     */
    @GetMapping("/3")
    public CompletableFuture<ResultData> completableFuture() {
        log.info("CompletableFuture start");
        CompletableFuture<ResultData> future = deferredService.completableFuture();
        future.whenComplete((result, e) -> {
            if (e != null) {
                log.error("error", e);
            } else {
                log.info("result: {}", result);
            }
        });
        log.info("CompletableFuture end");
        return future;
    }

    @ExceptionHandler(AsyncRequestTimeoutException.class)
    public ResponseEntity<Object> handleAsyncRequestTimeoutException(AsyncRequestTimeoutException e) {
        ResultData result = new ResultData("timeout", LocalDateTime.now(), LocalDateTime.now());
        return new ResponseEntity<>(result, null, HttpStatus.SERVICE_UNAVAILABLE);
    }
}
