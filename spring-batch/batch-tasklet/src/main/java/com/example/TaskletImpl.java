package com.example;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TaskletImpl implements Tasklet {
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        log.info("stepExecutionContext={}", chunkContext.getStepContext().getStepExecutionContext());
        contribution.incrementReadCount();
        contribution.incrementWriteCount(1);
        log.info("stepContribution={}", contribution);
        return RepeatStatus.FINISHED;
    }
}
