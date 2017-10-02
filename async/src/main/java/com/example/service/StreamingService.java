package com.example.service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * HTTP Streaming
 */
@Service
@Slf4j
public class StreamingService {

    private static final int DATA_COUNT = 5;
    private static final long SLEEP_SEC = 3;

    /**
     * ResponseBodyEmitter
     */
    @Async
    public void async(ResponseBodyEmitter emitter) {
        log.info("Service start");
        IntStream.rangeClosed(1, DATA_COUNT)
                 .forEach(i -> {
                     try {
                         TimeUnit.SECONDS.sleep(SLEEP_SEC);
                         String data = "data" + i;
                         emitter.send(data + "\r\n");
                         log.info("send: {}", data);
                     } catch (InterruptedException | IOException e) {
                         log.error("error", e);
                         emitter.completeWithError(e);
                     }
                 });
        emitter.complete();
        log.info("Service end");
    }

    /**
     * SseEmitter
     */
    @Async
    public void async(SseEmitter emitter) {
        log.info("Service start");
        IntStream.rangeClosed(1, DATA_COUNT)
                 .forEach(i -> {
                     try {
                         TimeUnit.SECONDS.sleep(SLEEP_SEC);
                         ResponseData data = new ResponseData("data" + i);
                         emitter.send(SseEmitter.event()
                                                .comment("comment" + i)
                                                .id("id" + i)
                                                .name("eventName" + i)
                                                .data(data));
                         log.info("send: {}", data);
                     } catch (InterruptedException | IOException e) {
                         log.error("error", e);
                         emitter.completeWithError(e);
                     }
                 });
        emitter.complete();
        log.info("Service end");
    }

    /**
     * StreamingResponseBody
     * Spring MVC管理下のthreadで実行される
     */
    public StreamingResponseBody streaming() {
        return outputStream -> {
            log.info("Service start");
            IntStream.rangeClosed(1, DATA_COUNT)
                     .forEach(i -> {
                         try {
                             TimeUnit.SECONDS.sleep(SLEEP_SEC);
                             String data = "data" + i;
                             outputStream.write((data + "\r\n").getBytes());
                             outputStream.flush();
                             log.info("send: {}", data);
                         } catch (InterruptedException | IOException e) {
                             log.error("error", e);
                         }
                     });
            log.info("Service end");
        };
    }

    @Data
    @AllArgsConstructor
    public static class ResponseData {
        private String message;
    }
}
