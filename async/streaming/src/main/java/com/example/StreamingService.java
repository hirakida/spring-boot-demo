package com.example;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter.SseEventBuilder;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StreamingService {

    @Async
    public void handle(SseEmitter emitter) {
        IntStream.rangeClosed(1, 5)
                 .forEach(i -> {
                     Map<String, Integer> data = Map.of("data", i);
                     SseEventBuilder builder = SseEmitter.event()
                                                         .data(data);
                     try {
                         emitter.send(builder);
                         TimeUnit.SECONDS.sleep(1);
                     } catch (IOException | InterruptedException e) {
                         log.error("{}", e.getMessage(), e);
                         emitter.completeWithError(e);
                     }
                     log.info("send: {}", data);
                 });
        emitter.complete();
    }

    @Async
    public void handle(ResponseBodyEmitter emitter) {
        IntStream.rangeClosed(1, 5)
                 .forEach(i -> {
                     String data = "data" + i;
                     try {
                         emitter.send(data);
                         TimeUnit.SECONDS.sleep(1);
                     } catch (IOException | InterruptedException e) {
                         log.error("{}", e.getMessage(), e);
                         emitter.completeWithError(e);
                     }
                     log.info("send: {}", data);
                 });
        emitter.complete();
    }
}
