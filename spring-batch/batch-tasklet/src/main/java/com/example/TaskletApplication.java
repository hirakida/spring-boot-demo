package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.Scheduled;

import com.example.batch.JobService;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@EnableJpaAuditing
@RequiredArgsConstructor
public class TaskletApplication {

    private final JobService jobService;

    @Scheduled(fixedRate = 10000)
    public void job1() {
        jobService.job1();
    }

    public static void main(String[] args) {
        SpringApplication.run(TaskletApplication.class, args);
    }
}
