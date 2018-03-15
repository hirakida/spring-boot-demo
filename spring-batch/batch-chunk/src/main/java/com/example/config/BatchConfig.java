package com.example.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.batch.MyItemProcessor;
import com.example.batch.MyItemReader;
import com.example.batch.MyItemWriter;
import com.example.entity.Member;
import com.example.entity.User;
import com.example.batch.listener.MyJobExecutionListener;
import com.example.batch.listener.MyStepExecutionListener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
@Slf4j
public class BatchConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final MyItemReader myItemReader;
    private final MyItemProcessor myItemProcessor;
    private final MyItemWriter myItemWriter;
    private final MyJobExecutionListener myJobExecutionListener;
    private final MyStepExecutionListener myStepExecutionListener;

    @Bean
    public Job job1() {
        return jobBuilderFactory.get("job1")
                                .incrementer(new RunIdIncrementer())
                                .listener(myJobExecutionListener)
                                .start(step1())
                                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<User, Member>chunk(1)
                .reader(myItemReader)
                .processor(myItemProcessor)
                .writer(myItemWriter)
                .listener(myStepExecutionListener)
                .build();
    }
}
