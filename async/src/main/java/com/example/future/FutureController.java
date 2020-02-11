package com.example.future;

import java.util.concurrent.CompletableFuture;

import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Result;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class FutureController {
    private final FutureService futureService;

    @GetMapping("/future/1")
    public ListenableFuture<Result> listenableFuture() {
        return futureService.listenableFuture();
    }

    @GetMapping("/future/2")
    public CompletableFuture<Result> completableFuture() {
        return futureService.completableFuture();
    }
}
