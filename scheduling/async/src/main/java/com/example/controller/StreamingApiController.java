package com.example.controller;

import java.util.concurrent.TimeUnit;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import lombok.extern.slf4j.Slf4j;

/**
 * 非同期処理が終了してからhttp responseを送信する
 * Spring MVC管理下のスレッド(MvcAsync1など)で実行する
 */
@RestController
@Slf4j
public class StreamingApiController {

    /**
     * OutputStreamにdataを直接出力する
     */
    @GetMapping("/streaming")
    public StreamingResponseBody streaming() {
        log.info("Streaming Controller start");

        StreamingResponseBody responseBody = outputStream -> {
            log.info("Streaming start");
            for (int i = 0; i < 5; i++) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    log.error("error", e);
                }
                outputStream.write(("msg" + i + "\r\n").getBytes());
                outputStream.flush();
            }
            log.info("Streaming end");
        };

        log.info("Streaming Controller end");
        return responseBody;
    }
}
