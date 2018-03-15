package com.example;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;

import com.example.service.FutureService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ApiController {

    private final FutureService futureService;

    @GetMapping("/1")
    public ResultData future(@RequestParam(defaultValue = "3") long sleep) {
        log.info("Future start");
        Future<ResultData> future = futureService.future(sleep);
        ResultData result;
        try {
            result = future.get();
        } catch (ExecutionException | InterruptedException e) {
            log.error(e.toString());
            result = new ResultData(e.toString(), LocalDateTime.now());
        }
        log.info("Future end");
        return result;
    }

    @GetMapping("/2")
    public ListenableFuture<ResultData> listenableFuture(@RequestParam(defaultValue = "3") long sleep) {
        log.info("ListenableFuture start");
        ListenableFuture<ResultData> future = futureService.listenableFuture(sleep);
        future.addCallback(result -> log.info("result: {}", result),
                           e -> log.error("error", e));
        log.info("ListenableFuture end");
        return future;
    }

    @GetMapping("/3")
    public CompletableFuture<ResultData> completableFuture(@RequestParam(defaultValue = "3") long sleep) {
        log.info("CompletableFuture start");
        CompletableFuture<ResultData> future = futureService.completableFuture(sleep);
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
