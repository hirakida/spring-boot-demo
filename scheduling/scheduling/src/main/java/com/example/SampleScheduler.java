package com.example;

import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SampleScheduler {

    private static final String ZONE = "Asia/Tokyo";

    /**
     * 毎分0秒に実行する（1分に1回）
     * cronは左から(秒、分、時、日、月、曜日)
     */
    @Scheduled(cron = "0 * * * * *", zone = ZONE)
    public void cronSecTest() {
        log.info("[{}][cron 0 * * * * *]", LocalDateTime.now());
    }

    /**
     * 毎分0,15,30,45秒に実行する（1分に4回）
     */
    @Scheduled(cron = "0,15,30,45 * * * * *", zone = ZONE)
    public void cronSec2Test() {
        log.info("[{}][cron 0,15,30,45 * * * * *]", LocalDateTime.now());
    }

    /**
     * 毎分0-15秒に実行する（1分に15回）
     */
    @Scheduled(cron = "1-15 * * * * *", zone = ZONE)
    public void cronSec3Test() {
        log.info("[{}][cron 1-15 * * * * *]", LocalDateTime.now());
    }

    /**
     * 10秒毎に実行する（1分に6回）
     */
    @Scheduled(cron = "*/10 * * * * *", zone = ZONE)
    public void cronSec4Test() {
        log.info("[{}][cron */10 * * * * *]", LocalDateTime.now());
    }

    /**
     * 毎時0分0秒に実行する（1時間に1回）
     */
    @Scheduled(cron = "0 0 * * * *", zone = ZONE)
    public void cronMinTest() {
        log.info("[{}][cron 0 0 * * * *]", LocalDateTime.now());
    }

    /**
     * 毎日0時0分0秒に実行する（1日に1回）
     */
    @Scheduled(cron = "0 0 0 * * *", zone = ZONE)
    public void cronTimeTest() {
        log.info("[{}][cron 0 0 0 * * *]", LocalDateTime.now());
    }

    /**
     * taskの実行完了時点から5sec後に次のtaskを実行する
     */
    @Scheduled(fixedDelay = 5000)
    public void fixedDelayTest() {
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
    public void fixedRateTest() {
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
    public void initialDelayTest() {
        log.info("[{}][initialDelay]", LocalDateTime.now());
    }
}
