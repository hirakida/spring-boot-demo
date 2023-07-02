package com.example;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final JobLauncher jobLauncher;
    private final Job job1;
    private final Job job2;

    @Override
    public void run(String... args) {
        runJob(job1);
        runJob(job2);
    }

    private void runJob(Job job) {
        try {
            log.info("##### {} start #####", job.getName());
            jobLauncher.run(job, new JobParameters());
            log.info("##### {} end #####", job.getName());
        } catch (JobExecutionException e) {
            log.error(e.getMessage(), e);
        }
    }
}
