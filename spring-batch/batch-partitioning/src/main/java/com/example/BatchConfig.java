package com.example;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.partition.PartitionHandler;
import org.springframework.batch.core.partition.support.TaskExecutorPartitionHandler;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfig {
    @Bean
    public Job job1(JobRepository jobRepository, Step step1Manager) {
        return new JobBuilder("job1", jobRepository)
                .start(step1Manager)
                .build();
    }

    @Bean
    public Step step1Manager(JobRepository jobRepository, PartitionHandler partitionHandler) {
        return new StepBuilder("step1Manager", jobRepository)
                .partitioner("step1", new PartitionerImpl())
                .partitionHandler(partitionHandler)
                .build();
    }

    @Bean
    public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager,
                      TaskletImpl tasklet) {
        return new StepBuilder("step1", jobRepository)
                .tasklet(tasklet, transactionManager)
                .build();
    }

    @Bean
    public PartitionHandler partitionHandler(Step step1) {
        TaskExecutorPartitionHandler handler = new TaskExecutorPartitionHandler();
        handler.setGridSize(10);
        handler.setTaskExecutor(taskExecutor());
        handler.setStep(step1);
        return handler;
    }

    @Bean
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setThreadNamePrefix("partitioner-");
        executor.initialize();
        return executor;
    }
}
