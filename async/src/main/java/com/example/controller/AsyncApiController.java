package com.example.controller;

import java.util.concurrent.Future;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ResultData;
import com.example.service.AsyncService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/async")
@RequiredArgsConstructor
@Slf4j
public class AsyncApiController {

    private final AsyncService asyncService;

    /**
     * Async methodの戻り値がvoidの場合
     */
    @GetMapping("/1")
    public void asyncVoid() {
        log.info("async1 Controller start");
        asyncService.async();
        log.info("async1 Controller end");
    }

    /**
     * Future
     */
    @GetMapping("/2")
    public ResultData future() throws Exception {
        log.info("Future start");
        Future<ResultData> future = asyncService.future();
        ResultData result = future.get();
        log.info("Future end");
        return result;
    }
}
