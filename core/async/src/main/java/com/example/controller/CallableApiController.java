package com.example.controller;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * 非同期処理が終了してからhttp responseを送信する
 * Spring MVC管理下のスレッド(MvcAsync1など)で実行する
 */
@RestController
@Slf4j
public class CallableApiController {

    /**
     * Callable
     */
    @GetMapping("/callable")
    public Callable<String> callable() {
        log.info("Callable Controller start");

        Callable<String> callable = () -> {
            log.info("Callable start");
            TimeUnit.SECONDS.sleep(5);
            log.info("Callable end");
            return "Callable result";
        };

        log.info("Callable Controller end");
        return callable;
    }
}
