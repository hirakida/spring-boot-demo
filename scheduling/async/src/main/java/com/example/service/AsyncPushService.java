package com.example.service;

import java.io.IOException;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import lombok.extern.slf4j.Slf4j;

/**
 * Push型の非同期処理
 */
@Service
@Slf4j
public class AsyncPushService {

    @Async
    public void async(ResponseBodyEmitter emitter) {
        log.info("Service start");
        for (int i = 0; i < 3; i++) {
            sleep(1000L);
            try {
                emitter.send("msg" + i + "\r\n");
                log.info("send");
            } catch (IOException e) {
                log.error("error", e);
            }
        }
        emitter.complete();
        log.info("Service end");
    }

    @Async
    public void async(SseEmitter emitter, long millis) {
        log.info("Service start");
        for (int i = 0; i < 3; i++) {
            sleep(millis);
            try {
                emitter.send(SseEmitter.event()
                                       .id("id" + i)
                                       .data("data" + i));
                log.info("send");
            } catch (IOException e) {
                log.error("error", e);
            }
        }
        emitter.complete();
        log.info("Service end");
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            log.error("error", e);
        }
    }
}
