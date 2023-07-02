package com.example;

import java.time.LocalTime;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfig {
    @Bean
    public Job job1(JobRepository jobRepository, Step step1, Step step2, Step step3, Step step4) {
        return new JobBuilder("job1", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(step1)
                .next(step2)
                .next(step3)
                .next(step4)
                .build();
    }

    @Bean
    public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager,
                      TaskletImpl tasklet1) {
        return new StepBuilder("step1", jobRepository)
                .tasklet(tasklet1, transactionManager)
                .build();
    }

    @Bean
    public Step step2(JobRepository jobRepository, PlatformTransactionManager transactionManager,
                      TaskletImpl tasklet2) {
        return new StepBuilder("step2", jobRepository)
                .tasklet(tasklet2, transactionManager)
                .build();
    }

    @Bean
    public Step step3(JobRepository jobRepository, PlatformTransactionManager transactionManager,
                      TaskletImpl tasklet1) {
        return new StepBuilder("step3", jobRepository)
                .tasklet(tasklet1, transactionManager)
                .build();
    }

    @Bean
    public Step step4(JobRepository jobRepository, PlatformTransactionManager transactionManager,
                      TaskletImpl tasklet2) {
        return new StepBuilder("step4", jobRepository)
                .tasklet(tasklet2, transactionManager)
                .build();
    }

    @JobScope
    @Bean
    public TaskletImpl tasklet1() {
        return new TaskletImpl(LocalTime.now());
    }

    @StepScope
    @Bean
    public TaskletImpl tasklet2() {
        return new TaskletImpl(LocalTime.now());
    }
}
