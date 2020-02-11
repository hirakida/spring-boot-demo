package com.example.streaming;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class StreamingController {
    private static final long TIMEOUT = 5000;
    private final StreamingService streamingService;

    @GetMapping("/streaming/1")
    public ResponseBodyEmitter responseBodyEmitter() {
        ResponseBodyEmitter emitter = new ResponseBodyEmitter(TIMEOUT);
        streamingService.responseBodyEmitter(emitter);
        return emitter;
    }

    @GetMapping("/streaming/2")
    public StreamingResponseBody streamingResponseBody() {
        return streamingService.streamingResponseBody();
    }
}
