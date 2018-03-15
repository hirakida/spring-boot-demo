package com.example;

import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * cron
 * - second       (0 - 59)
 * - minute       (0 - 59)
 * - hour         (0 - 23)
 * - day of month (1 - 31)
 * - month        (1 - 12)
 * - day of week  (0 - 6)
 */
@Component
@Slf4j
public class CronTasks {

    private static final String ZONE = "Asia/Tokyo";

    /**
     * Once a minute
     */
    @Scheduled(cron = "0 * * * * *", zone = ZONE)
    public void onceAMinute() {
        log.info("[{}][cron 0 * * * * *]", LocalDateTime.now());
    }

    /**
     * 4 times a minute
     */
    @Scheduled(cron = "0,15,30,45 * * * * *", zone = ZONE)
    public void fourTimesAMinute() {
        log.info("[{}][cron 0,15,30,45 * * * * *]", LocalDateTime.now());
    }

    /**
     * 15 times a minute
     */
    @Scheduled(cron = "1-15 * * * * *", zone = ZONE)
    public void fifteenTimesAMinute() {
        log.info("[{}][cron 1-15 * * * * *]", LocalDateTime.now());
    }

    /**
     * Every 10 seconds (0, 10, 20, 30, 40, 50)
     */
    @Scheduled(cron = "*/10 * * * * *", zone = ZONE)
    public void every10Seconds() {
        log.info("[{}][cron */10 * * * * *]", LocalDateTime.now());
    }

    /**
     * Every 3 minutes
     */
    @Scheduled(cron = "0 */3 * * * *", zone = ZONE)
    public void every3Minutes() {
        log.info("[{}][cron 0 */3 * * * *]", LocalDateTime.now());
    }

    /**
     * Once an hour (xx:00.00)
     */
    @Scheduled(cron = "0 0 * * * *", zone = ZONE)
    public void onceAnHour() {
        log.info("[{}][cron 0 0 * * * *]", LocalDateTime.now());
    }

    /**
     * Once a day (00:00.00)
     */
    @Scheduled(cron = "0 0 0 * * *", zone = ZONE)
    public void onceADay() {
        log.info("[{}][cron 0 0 0 * * *]", LocalDateTime.now());
    }
}
