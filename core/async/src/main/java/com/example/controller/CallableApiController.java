package com.example.controller;

import java.util.concurrent.Callable;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.WebAsyncTask;

import com.example.ResultData;
import com.example.service.CallableService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/callable")
@RequiredArgsConstructor
@Slf4j
public class CallableApiController {

    private final CallableService callableService;

    @GetMapping("/1")
    public Callable<ResultData> callable() {
        log.info("Callable start");
        Callable<ResultData> callable = callableService.callable();
        log.info("Callable end");
        return callable;
    }

    @GetMapping("/2")
    public WebAsyncTask<ResultData> webAsyncTask() {
        log.info("Callable start");
        Callable<ResultData> callable = callableService.callable();
        // 引数にtimeoutを指定できる
        WebAsyncTask<ResultData> task = new WebAsyncTask<>(callable);
        log.info("Callable end");
        return task;
    }
}
