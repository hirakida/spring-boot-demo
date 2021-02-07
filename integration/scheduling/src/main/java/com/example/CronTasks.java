package com.example;

import java.time.LocalTime;

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
        log.info("[{}][{}]", LocalTime.now(), environment.getProperty("cron.once_a_minute"));
    }

    /**
     * 4 times a minute
     */
    @Scheduled(cron = "0,15,30,45 * * * * *", zone = ZONE)
    public void fourTimesAMinute() {
        log.info("[{}][{}]", LocalTime.now(), "0,15,30,45 * * * * *");
    }

    /**
     * 10 times a minute (1 - 10)
     */
    @Scheduled(cron = "1-10 * * * * *", zone = ZONE)
    public void fifteenTimesAMinute() {
        log.info("[{}][{}]", LocalTime.now(), "1-10 * * * * *");
    }

    /**
     * Every 10 seconds (0, 10, 20, 30, 40, 50)
     */
    @Scheduled(cron = "*/10 * * * * *", zone = ZONE)
    public void every10Seconds() {
        log.info("[{}][{}]", LocalTime.now(), "*/10 * * * * *");
    }

    /**
     * Every 3 minutes
     */
    @Scheduled(cron = "0 */3 * * * *", zone = ZONE)
    public void every3Minutes() {
        log.info("[{}][{}]", LocalTime.now(), "0 */3 * * * *");
    }

    /**
     * Once an hour (xx:00.00)
     */
    @Scheduled(cron = "0 0 * * * *", zone = ZONE)
    public void onceAnHour() {
        log.info("[{}][{}]", LocalTime.now(), "0 0 * * * *");
    }

    /**
     * Once a day (00:00.00)
     */
    @Scheduled(cron = "0 0 0 * * *", zone = ZONE)
    public void onceADay() {
        log.info("[{}][{}]", LocalTime.now(), "0 0 0 * * *");
    }
}
