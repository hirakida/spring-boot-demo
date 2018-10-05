package com.example;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class CronTasks {

    private final JobLauncher jobLauncher;
    private final Job job1;
    private final Job job2;
    private final Job job3;

    @Scheduled(cron = "0 * * * * *")
    public void job1() {
        job(job1);
    }

    @Scheduled(cron = "20 * * * * *")
    public void job2() {
        job(job2);
    }

    @Scheduled(cron = "40 * * * * *")
    public void job3() {
        job(job3);
    }

    private void job(Job job) {
        Map<String, JobParameter> param = new HashMap<>();
        param.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters jobParameters = new JobParameters(param);
        try {
            log.info("##### {} start #####", job.getName());
            jobLauncher.run(job, jobParameters);
            log.info("##### {} end   #####", job.getName());
        } catch (JobExecutionAlreadyRunningException |
                JobInstanceAlreadyCompleteException |
                JobParametersInvalidException |
                JobRestartException e) {
            log.error(e.toString());
        }
    }
}
