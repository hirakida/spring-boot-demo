package com.example.controller;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.context.request.async.DeferredResult;

import com.example.service.AsyncService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 非同期処理が終了してからhttp responseを送信する
 * Spring MVC管理外のスレッドで実行する
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class AsyncApiController {

    private final AsyncService asyncService;

    @GetMapping("/async1")
    public void async1() {
        log.info("async1 Controller start");
        asyncService.async();
        log.info("async1 Controller end");
    }

    // DeferredResultを使って非同期処理の結果をSpring MVCに通知する
    @GetMapping("/async2")
    public DeferredResult<Result> deferredResult() {
        log.info("async2 Controller start");
        DeferredResult<Result> deferredResult = new DeferredResult<>();
        asyncService.async(deferredResult);
        log.info("async2 Controller end");
        return deferredResult;
    }

    // ListenableFutureを使う
    // springが提供しているFutureのsub interface
    @GetMapping("/async3")
    public ListenableFuture<Result> listenableFuture() {
        log.info("async3 Controller start");
        ListenableFuture<Result> future = asyncService.listenableFuture();
        future.addCallback(result -> log.info("{}", result),
                           e -> log.error("error", e));
        log.info("async3 Controller end");
        return future;
    }

    // java標準のCompletableFutureを使う
    @GetMapping("/async4")
    public CompletableFuture<Result> completableFuture(@RequestParam(defaultValue = "1000") long millis) {
        log.info("async4 Controller start");
        CompletableFuture<Result> future = asyncService.completableFuture(millis);
        future.thenAccept(result -> log.info("{}", result));
        log.info("async Controller end");
        return future;
    }

    @ExceptionHandler(AsyncRequestTimeoutException.class)
    public ResponseEntity<Object> handleAsyncRequestTimeoutException(AsyncRequestTimeoutException e) {
        Result result = new Result("timeout", LocalDateTime.now(), LocalDateTime.now());
        return new ResponseEntity<>(result, null, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Result {
        private String message;
        private LocalDateTime startAt;
        private LocalDateTime endAt;

        public static Result of(String message, LocalDateTime startAt) {
            Result result = new Result();
            result.setMessage(message);
            result.setStartAt(startAt);
            return result;
        }
    }
}
