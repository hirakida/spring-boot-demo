package com.example.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.listener.MyJobExecutionListener;
import com.example.listener.MyStepExecutionListener;
import com.example.tasklet.Tasklet1;
import com.example.tasklet.Tasklet2;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final Tasklet1 tasklet1;
    private final Tasklet2 tasklet2;

    @Bean
    public Job job1() {
        return jobBuilderFactory.get("job1")
                                .incrementer(new RunIdIncrementer())
                                .listener(myJobExecutionListener())
                                .start(step1())
                                .build();
    }

    @Bean
    public Job job2() {
        return jobBuilderFactory.get("job2")
                                .incrementer(new RunIdIncrementer())
                                .listener(myJobExecutionListener())
                                .start(step2())
                                .build();
    }

    @Bean
    public Job job3() {
        return jobBuilderFactory.get("job3")
                                .incrementer(new RunIdIncrementer())
                                .listener(myJobExecutionListener())
                                .start(step1())
                                .next(step2())
                                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                                 .tasklet(tasklet1)
                                 .listener(myStepExecutionListener())
                                 .build();
    }

    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2")
                                 .tasklet(tasklet2)
                                 .listener(myStepExecutionListener())
                                 .build();
    }

    @Bean
    public MyJobExecutionListener myJobExecutionListener() {
        return new MyJobExecutionListener();
    }

    @Bean
    public MyStepExecutionListener myStepExecutionListener() {
        return new MyStepExecutionListener();
    }
}
