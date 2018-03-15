package com.example;

import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.example.service.StreamingService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ApiController {

    private final StreamingService streamingService;

    @GetMapping("/1")
    public ResponseBodyEmitter responseBodyEmitter() {
        log.info("ResponseBodyEmitter start");
        long timeout = 5000;
        ResponseBodyEmitter emitter = new ResponseBodyEmitter(timeout);
        streamingService.async(emitter);
        log.info("ResponseBodyEmitter end");
        return emitter;
    }

    @GetMapping("/2")
    public SseEmitter sseEmitter() {
        log.info("SseEmitter start");
        SseEmitter emitter = new SseEmitter();
        streamingService.async(emitter);
        log.info("SseEmitter end");
        return emitter;
    }

    @GetMapping("/3")
    public StreamingResponseBody streamingResponseBody() {
        log.info("StreamingResponseBody start");
        StreamingResponseBody responseBody = streamingService.streaming();
        log.info("StreamingResponseBody end");
        return responseBody;
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
