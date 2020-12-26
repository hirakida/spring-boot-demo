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
    public void handle(ResponseBodyEmitter emitter) {
        IntStream.rangeClosed(11, 20)
                 .forEach(i -> {
                     sleep(1);
                     String data = "data" + i;
                     log.info("{}", data);

                     try {
                         emitter.send(data + "\r\n");
                     } catch (IOException e) {
                         log.error("{}", e.getMessage(), e);
                         emitter.completeWithError(e);
                     }
                 });
        emitter.complete();
    }

    @Async
    public void handle(SseEmitter emitter) {
        IntStream.rangeClosed(1, 10)
                 .forEach(i -> {
                     sleep(1);
                     Map<String, Integer> data = Map.of("data", i);
                     SseEventBuilder builder = SseEmitter.event().data(data);
                     log.info("{}", data);

                     try {
                         emitter.send(builder);
                     } catch (IOException e) {
                         log.error("{}", e.getMessage(), e);
                         emitter.completeWithError(e);
                     }
                 });
        emitter.complete();
    }

    private static void sleep(long timeout) {
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            log.error("{}", e.getMessage(), e);
        }
    }
}
