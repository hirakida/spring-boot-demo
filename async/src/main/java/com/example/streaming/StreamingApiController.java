package com.example.streaming;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class StreamingApiController {
    private static final long TIMEOUT = 5000;
    private final StreamingService streamingService;

    @GetMapping("/streaming/1")
    public ResponseBodyEmitter responseBodyEmitter() {
        log.info("ResponseBodyEmitter start");
        ResponseBodyEmitter emitter = new ResponseBodyEmitter(TIMEOUT);
        streamingService.responseBodyEmitter(emitter);
        log.info("ResponseBodyEmitter end");
        return emitter;
    }

    @GetMapping("/streaming/2")
    public SseEmitter sseEmitter() {
        log.info("SseEmitter start");
        SseEmitter emitter = new SseEmitter();
        streamingService.sseEmitter(emitter);
        log.info("SseEmitter end");
        return emitter;
    }

    @GetMapping("/streaming/3")
    public StreamingResponseBody streamingResponseBody() {
        log.info("StreamingResponseBody start");
        StreamingResponseBody responseBody =
                outputStream -> IntStream.range(0, 5)
                                         .forEach(i -> {
                                             try {
                                                 TimeUnit.SECONDS.sleep(1);
                                                 String data = "data" + i;
                                                 outputStream.write((data + "\r\n").getBytes());
                                                 outputStream.flush();
                                                 log.info("send: {}", data);
                                             } catch (InterruptedException | IOException e) {
                                                 log.error("{}", e.getMessage(), e);
                                             }
                                         });
        log.info("StreamingResponseBody end");
        return responseBody;
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
