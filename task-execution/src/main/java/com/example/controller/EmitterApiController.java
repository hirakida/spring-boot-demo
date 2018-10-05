package com.example.controller;

import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.example.service.EmitterService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class EmitterApiController {

    private static final long TIMEOUT = 5000;
    private final EmitterService emitterService;

    @GetMapping("/emitter1")
    public ResponseBodyEmitter responseBodyEmitter() {
        log.info("ResponseBodyEmitter start");
        ResponseBodyEmitter emitter = new ResponseBodyEmitter(TIMEOUT);
        emitterService.responseBodyEmitter(emitter);
        log.info("ResponseBodyEmitter end");
        return emitter;
    }

    @GetMapping("/emitter2")
    public SseEmitter sseEmitter() {
        log.info("SseEmitter start");
        SseEmitter emitter = new SseEmitter();
        emitterService.sseEmitter(emitter);
        log.info("SseEmitter end");
        return emitter;
    }

    @ExceptionHandler(AsyncRequestTimeoutException.class)
    public String handleAsyncRequestTimeoutException(AsyncRequestTimeoutException e) {
        log.error(e.toString());
        return SseEmitter.event()
                         .data("timeout")
                         .build().stream()
                         .map(d -> d.getData().toString())
                         .collect(Collectors.joining());
    }
}
