package com.example;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
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
    public Job job1(JobRepository jobRepository, Step step1, Step step2) {
        return new JobBuilder("job1", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(step1)
                .next(step2)
                .build();
    }

    @Bean
    public Job job2(JobRepository jobRepository, Step step1, Step step2) {
        return new JobBuilder("job2", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(step1)
                .on(ExitStatus.COMPLETED.getExitCode())
                .to(step2)
                .end()
                .build();
    }

    @Bean
    public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager,
                      TaskletImpl taskletImpl) {
        return new StepBuilder("step1", jobRepository)
                .tasklet(taskletImpl, transactionManager)
                .build();
    }

    @Bean
    public Step step2(JobRepository jobRepository, PlatformTransactionManager transactionManager,
                      TaskletImpl taskletImpl) {
        return new StepBuilder("step2", jobRepository)
                .tasklet(taskletImpl, transactionManager)
                .build();
    }
}
