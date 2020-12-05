package com.example;

import java.time.Clock;
import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class ScheduledTasks {
    private final Clock clock = Clock.systemDefaultZone();
    private final JobLauncher jobLauncher;
    private final Job job1;
    private final UserRepository userRepository;

    @Scheduled(fixedDelay = 10000)
    public void job1() {
        run(job1);
    }

    @Scheduled(initialDelay = 1000, fixedDelay = 10000)
    public void findAll() {
        userRepository.findAll()
                      .forEach(user -> log.info("{}", user));
    }

    private void run(Job job) {
        final JobParameters parameters = new JobParametersBuilder()
                .addDate("time", Date.from(clock.instant()))
                .toJobParameters();

        try {
            log.info("##### {} start #####", job.getName());
            jobLauncher.run(job, parameters);
            log.info("##### {} end #####", job.getName());
        } catch (JobExecutionException e) {
            log.error(e.getMessage(), e);
        }
    }
}
