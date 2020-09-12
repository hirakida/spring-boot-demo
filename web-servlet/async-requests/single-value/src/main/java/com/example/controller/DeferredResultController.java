package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.example.AsyncResponse;
import com.example.service.DeferredResultService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class DeferredResultController {
    private static final long TIMEOUT = 5000;
    private final DeferredResultService service;

    @GetMapping("/deferred_result")
    public DeferredResult<AsyncResponse> deferredResult() {
        DeferredResult<AsyncResponse> result = new DeferredResult<>(TIMEOUT);
        service.deferredResult(result);
        return result;
    }
}
