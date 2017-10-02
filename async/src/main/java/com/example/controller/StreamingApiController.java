package com.example.controller;

import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.example.service.StreamingService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * HTTP Streaming
 */
@RestController
@RequestMapping("/streaming")
@RequiredArgsConstructor
@Slf4j
public class StreamingApiController {

    private final StreamingService streamingService;

    /**
     * ResponseBodyEmitter
     */
    @GetMapping("/1")
    public ResponseBodyEmitter responseBodyEmitter() {
        log.info("ResponseBodyEmitter start");
        // 引数にtimeoutを指定できる
        ResponseBodyEmitter emitter = new ResponseBodyEmitter();
        streamingService.async(emitter);
        log.info("ResponseBodyEmitter end");
        return emitter;
    }

    /**
     * SseEmitter
     */
    @GetMapping("/2")
    public SseEmitter sseEmitter() {
        log.info("SseEmitter start");
        SseEmitter sseEmitter = new SseEmitter();
        streamingService.async(sseEmitter);
        log.info("SseEmitter end");
        return sseEmitter;
    }

    /**
     * StreamingResponseBody
     * OutputStreamにdataを直接出力する
     */
    @GetMapping("/3")
    public StreamingResponseBody streamingResponseBody() {
        log.info("StreamingResponseBody start");
        StreamingResponseBody responseBody = streamingService.streaming();
        log.info("StreamingResponseBody end");
        return responseBody;
    }

    @ExceptionHandler(AsyncRequestTimeoutException.class)
    public String handleAsyncRequestTimeoutException(AsyncRequestTimeoutException e) {
        log.error("error", e);
        return SseEmitter.event()
                         .data("timeout")
                         .build().stream()
                         .map(d -> d.getData().toString())
                         .collect(Collectors.joining());
    }
}
