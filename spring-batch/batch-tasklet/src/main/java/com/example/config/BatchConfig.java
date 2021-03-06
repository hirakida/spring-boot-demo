package com.example.config;

import java.time.LocalTime;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.listener.JobExecutionListenerImpl;
import com.example.listener.StepExecutionListenerImpl;
import com.example.batch.ReaderTasklet;
import com.example.batch.WriterTasklet;

import lombok.AllArgsConstructor;
import lombok.Data;
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
    public Job job1() {
        return jobBuilderFactory.get("job1")
                                .incrementer(new RunIdIncrementer())
                                .listener(jobExecutionListenerImpl)
                                .start(step1())
                                .build();
    }

    @Bean
    public Job job2() {
        return jobBuilderFactory.get("job2")
                                .incrementer(new RunIdIncrementer())
                                .listener(jobExecutionListenerImpl)
                                .start(step2())
                                .build();
    }

    @Bean
    public Job job3() {
        return jobBuilderFactory.get("job3")
                                .incrementer(new RunIdIncrementer())
                                .listener(jobExecutionListenerImpl)
                                .start(step1())
                                .next(step2())
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

    @Data
    @AllArgsConstructor
    public static class ScopeTime {
        private LocalTime time;
    }
}
