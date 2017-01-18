package com.example.controller;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.example.service.AsyncService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 非同期処理が終了してからhttp responseを送信する
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class ApiController {

    private final AsyncService asyncService;

    // Spring MVC管理下のスレッドで実行する
    @GetMapping("/callable")
    public Callable<String> callable(@RequestParam(defaultValue = "1") long sec) {
        log.info("Controller start");

        Callable<String> callable = () -> {
            log.info("Callable start");
            sleep(sec);
            log.info("Callable end");
            return "Callable";
        };

        log.info("Controller end");
        return callable;
    }

    @GetMapping("/streaming")
    public StreamingResponseBody streaming(@RequestParam(defaultValue = "1") long sec) {
        log.info("Controller start");

        StreamingResponseBody responseBody = outputStream -> {
            log.info("Streaming start");
            for (int i = 0; i < 5; i++) {
                sleep(sec);
                outputStream.write(("msg" + i + "\r\n").getBytes());
                outputStream.flush();
            }
            log.info("Streaming end");
        };

        log.info("Controller end");
        return responseBody;
    }

    private static void sleep(long sec) {
        try {
            TimeUnit.SECONDS.sleep(sec);
        } catch (InterruptedException e) {
            log.error("error", e);
        }
    }
}
