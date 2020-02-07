package com.example.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.User;
import com.example.batch.ItemProcessorImpl;
import com.example.batch.ItemReaderImpl;
import com.example.batch.ItemWriterImpl;
import com.example.batch.JobExecutionListenerImpl;
import com.example.batch.StepExecutionListenerImpl;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class BatchConfig {
    private static final String JOB_NAME = "job1";
    private static final String STEP_NAME = "step1";
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final ItemReaderImpl itemReaderImpl;
    private final ItemProcessorImpl itemProcessorImpl;
    private final ItemWriterImpl itemWriterImpl;
    private final JobExecutionListenerImpl jobExecutionListenerImpl;
    private final StepExecutionListenerImpl stepExecutionListenerImpl;

    @Bean(name = JOB_NAME)
    public Job job() {
        return jobBuilderFactory.get(JOB_NAME)
                                .incrementer(new RunIdIncrementer())
                                .listener(jobExecutionListenerImpl)
                                .start(step())
                                .build();
    }

    @Bean(name = STEP_NAME)
    public Step step() {
        return stepBuilderFactory.get(STEP_NAME)
                .<User, User>chunk(1)
                .reader(itemReaderImpl)
                .processor(itemProcessorImpl)
                .writer(itemWriterImpl)
                .listener(stepExecutionListenerImpl)
                .build();
    }
}
