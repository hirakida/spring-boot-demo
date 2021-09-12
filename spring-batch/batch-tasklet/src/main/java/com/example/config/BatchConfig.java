package com.example.config;

import java.time.LocalTime;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.partition.PartitionHandler;
import org.springframework.batch.core.partition.support.TaskExecutorPartitionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.example.listener.JobExecutionListenerImpl;
import com.example.listener.StepExecutionListenerImpl;
import com.example.tasklet.ReaderTasklet;
import com.example.tasklet.WriterTasklet;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class BatchConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final JobExecutionListenerImpl jobExecutionListenerImpl;
    private final StepExecutionListenerImpl stepExecutionListenerImpl;
    private final WriterTasklet writerTasklet;
    private final ReaderTasklet readerTasklet;

    @Bean
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setThreadNamePrefix("thread-pool-");
        executor.initialize();
        return executor;
    }

    @Bean
    public Job job1() {
        return jobBuilderFactory.get("job1")
                                .incrementer(new RunIdIncrementer())
                                .listener(jobExecutionListenerImpl)
                                .start(step1())
                                .next(step2())
                                .build();
    }

    @Bean
    public Job job2() {
        return jobBuilderFactory.get("job2")
                                .incrementer(new RunIdIncrementer())
                                .listener(jobExecutionListenerImpl)
                                .start(step1())
                                .on(ExitStatus.COMPLETED.getExitCode())
                                .to(step2())
                                .end()
                                .build();
    }

    @Bean
    public Job job3() {
        Flow flow1 = new FlowBuilder<Flow>("flow1")
                .start(step1())
                .build();
        Flow flow2 = new FlowBuilder<Flow>("flow2")
                .split(taskExecutor())
                .add(new FlowBuilder<Flow>("flow2-1")
                             .from(step2())
                             .end(),
                     new FlowBuilder<Flow>("flow2-2")
                             .from(step2())
                             .end())
                .build();
        return jobBuilderFactory.get("job3")
                                .incrementer(new RunIdIncrementer())
                                .listener(jobExecutionListenerImpl)
                                .start(flow1)
                                .next(flow2)
                                .end()
                                .build();
    }

    @Bean
    public Job job4() {
        return jobBuilderFactory.get("job4")
                                .incrementer(new RunIdIncrementer())
                                .listener(jobExecutionListenerImpl)
                                .start(step1())
                                .next(masterStep())
                                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                                 .tasklet(writerTasklet)
                                 .listener(stepExecutionListenerImpl)
                                 .build();
    }

    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2")
                                 .tasklet(readerTasklet)
                                 .listener(stepExecutionListenerImpl)
                                 .build();
    }

    @Bean
    public Step masterStep() {
        return stepBuilderFactory.get("masterStep")
                                 .partitioner("step2", new PartitionerImpl())
                                 .partitionHandler(partitionHandler())
                                 .build();
    }

    @Bean
    public PartitionHandler partitionHandler() {
        TaskExecutorPartitionHandler handler = new TaskExecutorPartitionHandler();
        handler.setGridSize(3);
        handler.setTaskExecutor(taskExecutor());
        handler.setStep(step2());
        return handler;
    }

    @JobScope
    @Bean
    public ScopeTime jobScopeTime() {
        return new ScopeTime(LocalTime.now());
    }

    @StepScope
    @Bean
    public ScopeTime stepScopeTime() {
        return new ScopeTime(LocalTime.now());
    }
}
