package com.example;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class StreamingController {
    private static final long TIMEOUT = 5000;
    private final StreamingService streamingService;

    @GetMapping("/sse")
    public SseEmitter sse() {
        SseEmitter emitter = new SseEmitter();
        streamingService.handle(emitter);
        return emitter;
    }

    @GetMapping("/stream")
    public ResponseBodyEmitter stream() {
        ResponseBodyEmitter emitter = new ResponseBodyEmitter(TIMEOUT);
        streamingService.handle(emitter);
        return emitter;
    }

    @GetMapping("/rawdata")
    public StreamingResponseBody rawData() {
        return streamingResponseBody();
    }

    private static StreamingResponseBody streamingResponseBody() {
        return outputStream -> IntStream.rangeClosed(6, 10)
                                        .forEach(i -> {
                                            String data = "data" + i;
                                            try {
                                                outputStream.write((data + "\r\n").getBytes());
                                                outputStream.flush();
                                                TimeUnit.SECONDS.sleep(1);
                                            } catch (IOException | InterruptedException e) {
                                                log.error("{}", e.getMessage(), e);
                                            }
                                            log.info("send: {}", data);
                                        });
    }
}
