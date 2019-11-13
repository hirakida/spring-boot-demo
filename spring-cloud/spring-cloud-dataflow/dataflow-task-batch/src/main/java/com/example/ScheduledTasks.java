package com.example;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class ScheduledTasks {
    private static final AtomicLong ATOMIC_LONG = new AtomicLong(1);
    private final JobLauncher jobLauncher;
    private final Job initialJob;
    private final Job scheduledJob;

    @Scheduled(cron = "*/30 * * * * *")
    public void scheduledJob() {
        if (ATOMIC_LONG.get() == 1) {
            runJob(initialJob);
        }
        runJob(scheduledJob);
    }

    private void runJob(Job job) {
        Map<String, JobParameter> params = new HashMap<>();
        params.put("datetime", new JobParameter(LocalDateTime.now().toString()));
        params.put("count", new JobParameter(ATOMIC_LONG.getAndIncrement()));
        JobParameters jobParameters = new JobParameters(params);

        try {
            jobLauncher.run(job, jobParameters);
        } catch (JobExecutionException e) {
            log.error("{}", e.getMessage(), e);
        }
    }
}
