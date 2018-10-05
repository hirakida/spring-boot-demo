package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.example.service.StreamingService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class StreamingApiController {

    private final StreamingService streamingService;

    @GetMapping("/streaming")
    public StreamingResponseBody streamingResponseBody() {
        log.info("StreamingResponseBody start");
        StreamingResponseBody responseBody = streamingService.streaming();
        log.info("StreamingResponseBody end");
        return responseBody;
    }
}
