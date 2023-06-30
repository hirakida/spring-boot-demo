package com.example;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
    private final SuccessCount successCount;

    public ScheduledTasks(SuccessCount successCount) {
        successCount.setName("Success Count");
        this.successCount = successCount;
    }

    @Scheduled(fixedRate = 5000)
    public void run() {
        successCount.increment();
    }
}
