package com.example.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.entity.User;
import com.example.listener.JobExecutionListenerImpl;
import com.example.listener.StepExecutionListenerImpl;
import com.example.batch.Step1Processor;
import com.example.batch.Step1Reader;
import com.example.batch.Step1Writer;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class BatchConfig {
    private static final String JOB_NAME = "job1";
    private static final String STEP_NAME = "step1";
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final Step1Reader step1Reader;
    private final Step1Processor step1Processor;
    private final Step1Writer step1Writer;
    private final JobExecutionListenerImpl jobExecutionListenerImpl;
    private final StepExecutionListenerImpl stepExecutionListenerImpl;

    @Bean(name = JOB_NAME)
    public Job job1() {
        return jobBuilderFactory.get(JOB_NAME)
                                .incrementer(new RunIdIncrementer())
                                .listener(jobExecutionListenerImpl)
                                .start(step1())
                                .build();
    }

    @Bean(name = STEP_NAME)
    public Step step1() {
        return stepBuilderFactory.get(STEP_NAME)
                .<User, User>chunk(1)
                .reader(step1Reader)
                .processor(step1Processor)
                .writer(step1Writer)
                .listener(stepExecutionListenerImpl)
                .build();
    }
}
