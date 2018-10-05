package com.example.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyJobExecutionListener extends JobExecutionListenerSupport {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("beforeJob {}", jobExecution);
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info("afterJob {}", jobExecution);
    }
}
