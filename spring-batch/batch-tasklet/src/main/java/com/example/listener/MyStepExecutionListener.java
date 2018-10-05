package com.example.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyStepExecutionListener extends StepExecutionListenerSupport {

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.info("afterStep {}", stepExecution);
        return ExitStatus.COMPLETED;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        log.info("beforeStep {}", stepExecution);
    }
}
