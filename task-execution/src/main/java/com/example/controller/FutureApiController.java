package com.example.controller;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.ResultData;
import com.example.service.FutureService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class FutureApiController {

    private final FutureService futureService;

    @GetMapping("/future1")
    public ResultData future() {
        log.info("Future start");
        Future<ResultData> future = futureService.future();
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

    @GetMapping("/future2")
    public ListenableFuture<ResultData> listenableFuture() {
        log.info("ListenableFuture start");
        ListenableFuture<ResultData> future = futureService.listenableFuture();
        future.addCallback(result -> log.info("result: {}", result),
                           e -> log.error("error", e));
        log.info("ListenableFuture end");
        return future;
    }

    @GetMapping("/future3")
    public CompletableFuture<ResultData> completableFuture() {
        log.info("CompletableFuture start");
        CompletableFuture<ResultData> future = futureService.completableFuture();
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
}
