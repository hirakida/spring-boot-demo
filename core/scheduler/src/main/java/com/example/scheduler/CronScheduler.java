package com.example.scheduler;

import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CronScheduler {

    private static final String ZONE = "Asia/Tokyo";

    /**
     * 毎分0秒に実行する（1分に1回）
     * cronは左から(秒、分、時、日、月、曜日)
     */
    @Scheduled(cron = "0 * * * * *", zone = ZONE)
    public void cronSec() {
        log.info("[{}][cron 0 * * * * *]", LocalDateTime.now());
    }

    /**
     * 毎分0,15,30,45秒に実行する（1分に4回）
     */
    @Scheduled(cron = "0,15,30,45 * * * * *", zone = ZONE)
    public void cronSec2() {
        log.info("[{}][cron 0,15,30,45 * * * * *]", LocalDateTime.now());
    }

    /**
     * 毎分0-15秒に実行する（1分に15回）
     */
    @Scheduled(cron = "1-15 * * * * *", zone = ZONE)
    public void cronSec3() {
        log.info("[{}][cron 1-15 * * * * *]", LocalDateTime.now());
    }

    /**
     * 10秒毎に実行する（1分に6回）
     */
    @Scheduled(cron = "*/10 * * * * *", zone = ZONE)
    public void cronSec4() {
        log.info("[{}][cron */10 * * * * *]", LocalDateTime.now());
    }

    /**
     * 毎時0分0秒に実行する（1時間に1回）
     */
    @Scheduled(cron = "0 0 * * * *", zone = ZONE)
    public void cronMin() {
        log.info("[{}][cron 0 0 * * * *]", LocalDateTime.now());
    }

    /**
     * 毎日0時0分0秒に実行する（1日に1回）
     */
    @Scheduled(cron = "0 0 0 * * *", zone = ZONE)
    public void cronTime() {
        log.info("[{}][cron 0 0 0 * * *]", LocalDateTime.now());
    }
}
