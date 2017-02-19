package com.example.controller;

import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.example.service.AsyncPushService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 非同期処理中にresponse dataをpushする
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class AsyncPushApiController {

    private final AsyncPushService asyncPushService;

    /**
     * ResponseBodyEmitter
     */
    @GetMapping("/push1")
    public ResponseBodyEmitter push1() {
        log.info("Controller start");
        ResponseBodyEmitter emitter = new ResponseBodyEmitter();
        asyncPushService.async(emitter);
        log.info("Controller end");
        return emitter;
    }

    /**
     * SseEmitter
     */
    @GetMapping("/push2")
    public SseEmitter push2(@RequestParam(defaultValue = "1000") long millis) {
        log.info("Controller start");
        SseEmitter sseEmitter = new SseEmitter();
        asyncPushService.async(sseEmitter, millis);
        log.info("Controller end");
        return sseEmitter;
    }

    @ExceptionHandler(AsyncRequestTimeoutException.class)
    public String handleAsyncRequestTimeoutException(AsyncRequestTimeoutException e) {
        return SseEmitter.event()
                         .data("timeout")
                         .build().stream()
                         .map(d -> d.getData().toString())
                         .collect(Collectors.joining());
    }
}
