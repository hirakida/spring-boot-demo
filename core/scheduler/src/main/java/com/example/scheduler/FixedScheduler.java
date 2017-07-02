package com.example.scheduler;

import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FixedScheduler {

    /**
     * taskの実行完了時点から5sec後に次のtaskを実行する
     */
    @Scheduled(fixedDelay = 5000)
    public void fixedDelay() {
        log.info("[{}][fixedDelay]", LocalDateTime.now());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.warn("InterruptedException: ", e);
        }
    }

    /**
     * taskの実行開始時点から5sec後に次のtaskを実行する
     * 常に5秒ごとに実行される
     */
    @Scheduled(fixedRate = 5000)
    public void fixedRate() {
        log.info("[{}][fixedRate]", LocalDateTime.now());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.warn("InterruptedException: ", e);
        }
    }

    /**
     * 起動から10sec後にtaskを実行する
     * 以降は5sec毎に実行する
     */
    @Scheduled(initialDelay = 10000, fixedRate = 5000)
    public void initialDelay() {
        log.info("[{}][initialDelay]", LocalDateTime.now());
    }
}
