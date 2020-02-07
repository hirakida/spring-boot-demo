package com.example;

import java.util.Map;

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
public class CronTasks {
    private final JobLauncher jobLauncher;
    private final Job job1;
    private final UserRepository userRepository;

    @Scheduled(fixedDelay = 10000)
    public void job1() {
        run(job1);

        userRepository.findAll()
                      .forEach(user -> log.info("{}", user));
    }

    private void run(Job job) {
        Map<String, JobParameter> params = Map.of("time", new JobParameter(System.currentTimeMillis()));
        JobParameters jobParameters = new JobParameters(params);

        try {
            log.info("##### {} start #####", job.getName());
            jobLauncher.run(job, jobParameters);
            log.info("##### {} end   #####", job.getName());
        } catch (JobExecutionException e) {
            log.error(e.getMessage(), e);
        }
    }
}
