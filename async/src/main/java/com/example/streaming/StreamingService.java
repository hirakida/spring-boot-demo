package com.example.streaming;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StreamingService {

    @Async
    public void responseBodyEmitter(ResponseBodyEmitter emitter) {
        IntStream.range(0, 5)
                 .forEach(i -> {
                     try {
                         TimeUnit.SECONDS.sleep(1);
                         String data = "data" + i;
                         emitter.send(data + "\r\n");
                         log.info("send: {}", data);
                     } catch (InterruptedException | IOException e) {
                         log.error("{}", e.getMessage(), e);
                         emitter.completeWithError(e);
                     }
                 });
        emitter.complete();
    }

    @Async
    public void sseEmitter(SseEmitter emitter) {
        IntStream.range(0, 5)
                 .forEach(i -> {
                     try {
                         TimeUnit.SECONDS.sleep(1);
                         Map<String, Integer> data = Map.of("index", i);
                         emitter.send(SseEmitter.event()
                                                .comment("comment" + i)
                                                .id(String.valueOf(i))
                                                .name("name" + i)
                                                .data(data));
                         log.info("send: {}", data);
                     } catch (InterruptedException | IOException e) {
                         log.error("{}", e.getMessage(), e);
                         emitter.completeWithError(e);
                     }
                 });
        emitter.complete();
    }
}
