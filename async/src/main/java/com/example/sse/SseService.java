package com.example.sse;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter.SseEventBuilder;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SseService {

    @Async
    public void sseEmitter(SseEmitter emitter) {
        IntStream.range(0, 5)
                 .forEach(i -> {
                     sleep(1);

                     Map<String, Integer> data = Map.of("index", i);
                     SseEventBuilder builder = SseEmitter.event()
                                                         .comment("comment" + i)
                                                         .id(String.valueOf(i))
                                                         .name("name" + i)
                                                         .data(data);
                     try {
                         emitter.send(builder);
                     } catch (IOException e) {
                         log.error("{}", e.getMessage(), e);
                         emitter.completeWithError(e);
                     }
                     log.info("send: {}", data);
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
