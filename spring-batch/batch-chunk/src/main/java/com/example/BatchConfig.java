package com.example;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.example.listener.ChunkListenerImpl;
import com.example.listener.JobExecutionListenerImpl;
import com.example.listener.SkipListenerImpl;
import com.example.listener.StepExecutionListenerImpl;
import com.example.step.Step1Processor;
import com.example.step.Step1Reader;
import com.example.step.Step1Writer;

@Configuration
public class BatchConfig {
    @Bean
    public Job job1(JobRepository jobRepository, Step step1) {
        return new JobBuilder("job1", jobRepository)
                .incrementer(new RunIdIncrementer())
                .listener(new JobExecutionListenerImpl())
                .start(step1)
                .build();
    }

    @Bean
    public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager,
                      Step1Reader step1Reader, Step1Processor step1Processor, Step1Writer step1Writer) {
        return new StepBuilder("step1", jobRepository)
                .<User, User>chunk(1, transactionManager)
                .reader(step1Reader)
                .processor(step1Processor)
                .writer(step1Writer)
                .listener(new StepExecutionListenerImpl())
                .listener(new ChunkListenerImpl())
                .listener(new SkipListenerImpl<User, User>())
                .build();
    }
}
