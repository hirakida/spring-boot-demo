package com.example.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.http.HttpStatus;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.Result;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class FutureApiController {
    private final FutureService futureService;

    @GetMapping("/future/1")
    public Result future() {
        log.info("Future start");
        Future<Result> future = futureService.future();
        Result result;
        try {
            result = future.get();
        } catch (ExecutionException | InterruptedException e) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE);
        }
        log.info("Future end");
        return result;
    }

    @GetMapping("/future/2")
    public ListenableFuture<Result> listenableFuture() {
        log.info("ListenableFuture start");
        ListenableFuture<Result> future = futureService.listenableFuture();
        future.addCallback(result -> log.info("result: {}", result),
                           e -> log.error("{}", e.getMessage(), e));
        log.info("ListenableFuture end");
        return future;
    }

    @GetMapping("/future/3")
    public CompletableFuture<Result> completableFuture() {
        log.info("CompletableFuture start");
        CompletableFuture<Result> future = futureService.completableFuture();
        future.whenComplete((result, e) -> {
            if (e != null) {
                log.error("{}", e.getMessage(), e);
            } else {
                log.info("result: {}", result);
            }
        });
        log.info("CompletableFuture end");
        return future;
    }
}
