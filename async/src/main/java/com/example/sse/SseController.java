package com.example.sse;

import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SseController {
    private final SseService sseService;

    @GetMapping("/sse")
    public SseEmitter sseEmitter() {
        SseEmitter emitter = new SseEmitter();
        sseService.sseEmitter(emitter);
        return emitter;
    }

    @ExceptionHandler(AsyncRequestTimeoutException.class)
    public String handleAsyncRequestTimeoutException(AsyncRequestTimeoutException e) {
        log.error("{}", e.getMessage(), e);
        return SseEmitter.event()
                         .data("timeout")
                         .build()
                         .stream()
                         .map(d -> d.getData().toString())
                         .collect(Collectors.joining());
    }
}
