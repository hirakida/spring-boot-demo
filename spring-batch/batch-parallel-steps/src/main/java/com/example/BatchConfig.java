package com.example;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfig {
    @Bean
    public Job job1(JobRepository jobRepository, Flow flow) {
        return new JobBuilder("job1", jobRepository)
                .start(flow)
                .end()
                .build();
    }

    @Bean
    public Flow flow(Step step1, Step step2, Step step3, Step step4) {
        Flow flow1 = new FlowBuilder<Flow>("flow1")
                .start(step1)
                .next(step2)
                .build();
        Flow flow2 = new FlowBuilder<Flow>("flow2")
                .from(step3)
                .build();
        Flow flow3 = new FlowBuilder<Flow>("flow3")
                .from(step4)
                .build();
        return new FlowBuilder<Flow>("flow2")
                .split(taskExecutor())
                .add(flow1, flow2, flow3)
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
    public Step step2(JobRepository jobRepository, PlatformTransactionManager transactionManager,
                      TaskletImpl tasklet) {
        return new StepBuilder("step2", jobRepository)
                .tasklet(tasklet, transactionManager)
                .build();
    }

    @Bean
    public Step step3(JobRepository jobRepository, PlatformTransactionManager transactionManager,
                      TaskletImpl tasklet) {
        return new StepBuilder("step3", jobRepository)
                .tasklet(tasklet, transactionManager)
                .build();
    }

    @Bean
    public Step step4(JobRepository jobRepository, PlatformTransactionManager transactionManager,
                      TaskletImpl tasklet) {
        return new StepBuilder("step4", jobRepository)
                .tasklet(tasklet, transactionManager)
                .build();
    }

    @Bean
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setThreadNamePrefix("thread-pool-");
        executor.initialize();
        return executor;
    }
}
