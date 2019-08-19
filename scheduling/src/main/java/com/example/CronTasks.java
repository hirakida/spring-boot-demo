package com.example;

import java.time.LocalDateTime;

import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@Slf4j
public class CronTasks {
    private static final String ZONE = "Asia/Tokyo";
    private final Environment environment;

    /**
     * Once a minute
     */
    @Scheduled(cron = "${cron.once_a_minute}", zone = ZONE)
    public void onceAMinute() {
        log.info("[{}][{}]", LocalDateTime.now(), environment.getProperty("cron.once_a_minute"));
    }

    /**
     * 4 times per minute
     */
    @Scheduled(cron = "${cron.four_times_per_minute}", zone = ZONE)
    public void fourTimesAMinute() {
        log.info("[{}][0,15,30,45 * * * * *]", LocalDateTime.now());
    }

    /**
     * 10 times per minute
     */
    @Scheduled(cron = "${cron.ten_times_per_minute}", zone = ZONE)
    public void fifteenTimesAMinute() {
        log.info("[{}][1-10 * * * * *]", LocalDateTime.now());
    }

    /**
     * Every 10 seconds (0, 10, 20, 30, 40, 50)
     */
    @Scheduled(cron = "${cron.every_10_seconds}", zone = ZONE)
    public void every10Seconds() {
        log.info("[{}][*/10 * * * * *]", LocalDateTime.now());
    }

    /**
     * Every 3 minutes
     */
    @Scheduled(cron = "${cron.every_3_minutes}", zone = ZONE)
    public void every3Minutes() {
        log.info("[{}][0 */3 * * * *]", LocalDateTime.now());
    }

    /**
     * Once an hour (xx:00.00)
     */
    @Scheduled(cron = "${cron.once_an_hour}", zone = ZONE)
    public void onceAnHour() {
        log.info("[{}][{}]", LocalDateTime.now(), environment.getProperty("cron.once_an_hour"));
    }

    /**
     * Once a day (00:00.00)
     */
    @Scheduled(cron = "${cron.once_a_day}", zone = ZONE)
    public void onceADay() {
        log.info("[{}][{}]", LocalDateTime.now(), environment.getProperty("cron.once_a_day"));
    }
}
