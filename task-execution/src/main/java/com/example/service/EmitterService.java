package com.example.service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmitterService {

    private static final int DATA_COUNT = 5;
    private static final long TIMEOUT = 1;

    @Async
    public void responseBodyEmitter(ResponseBodyEmitter emitter) {
        log.info("Service start");
        IntStream.rangeClosed(1, DATA_COUNT)
                 .forEach(i -> {
                     try {
                         TimeUnit.SECONDS.sleep(TIMEOUT);
                         String data = "data" + i;
                         emitter.send(data + "\r\n");
                         log.info("send: {}", data);
                     } catch (InterruptedException | IOException e) {
                         log.error(e.toString());
                         emitter.completeWithError(e);
                     }
                 });
        emitter.complete();
        log.info("Service end");
    }

    @Async
    public void sseEmitter(SseEmitter emitter) {
        log.info("Service start");
        IntStream.rangeClosed(1, DATA_COUNT)
                 .forEach(i -> {
                     try {
                         TimeUnit.SECONDS.sleep(TIMEOUT);
                         ResponseData data = new ResponseData("data" + i);
                         emitter.send(SseEmitter.event()
                                                .comment("comment" + i)
                                                .id("id" + i)
                                                .name("eventName" + i)
                                                .data(data));
                         log.info("send: {}", data);
                     } catch (InterruptedException | IOException e) {
                         log.error(e.toString());
                         emitter.completeWithError(e);
                     }
                 });
        emitter.complete();
        log.info("Service end");
    }

    @Data
    @AllArgsConstructor
    public static class ResponseData {
        private String message;
    }
}
