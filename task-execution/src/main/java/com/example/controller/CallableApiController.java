package com.example.controller;

import java.util.concurrent.Callable;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.WebAsyncTask;

import com.example.model.ResultData;
import com.example.service.CallableService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CallableApiController {

    private static final long TIMEOUT = 3000;
    private final CallableService callableService;

    @GetMapping("/callable1")
    public Callable<ResultData> callable() {
        log.info("Callable start");
        Callable<ResultData> callable = callableService.callable();
        log.info("Callable end");
        return callable;
    }

    @GetMapping("/callable2")
    public WebAsyncTask<ResultData> webAsyncTask() {
        log.info("Callable start");
        Callable<ResultData> callable = callableService.callable();
        WebAsyncTask<ResultData> task = new WebAsyncTask<>(TIMEOUT, callable);
        log.info("Callable end");
        return task;
    }
}
