package com.example;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MyStepListener extends StepExecutionListenerSupport {

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.info("{}", stepExecution);
        return ExitStatus.COMPLETED;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        log.info("{}", stepExecution);
    }
}
