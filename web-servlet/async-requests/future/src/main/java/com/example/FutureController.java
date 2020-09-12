package com.example;

import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class FutureController {
    private final FutureService futureService;

    @GetMapping("/future/1")
    public ListenableFuture<Response> listenableFuture() {
        return futureService.listenableFuture();
    }

    @GetMapping("/future/2")
    public CompletableFuture<Response> completableFuture() {
        return futureService.completableFuture();
    }

    @ExceptionHandler(AsyncRequestTimeoutException.class)
    public ResponseEntity<Object> handleAsyncRequestTimeoutException(AsyncRequestTimeoutException e) {
        log.error("{}", e.getMessage(), e);
        return new ResponseEntity<>(null, null, HttpStatus.SERVICE_UNAVAILABLE);
    }
}
