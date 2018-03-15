package com.example;

import java.util.concurrent.Callable;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.WebAsyncTask;

import com.example.service.CallableService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ApiController {

    private final CallableService callableService;

    @GetMapping("/1")
    public Callable<ResultData> callable(@RequestParam(defaultValue = "3") long sleep) {
        log.info("Callable start");
        Callable<ResultData> callable = callableService.callable(sleep);
        log.info("Callable end");
        return callable;
    }

    @GetMapping("/2")
    public WebAsyncTask<ResultData> webAsyncTask(@RequestParam(defaultValue = "3") long sleep) {
        log.info("Callable start");
        Callable<ResultData> callable = callableService.callable(sleep);
        long timeout = 3000;
        WebAsyncTask<ResultData> task = new WebAsyncTask<>(timeout, callable);
        log.info("Callable end");
        return task;
    }
}
