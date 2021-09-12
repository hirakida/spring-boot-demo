package com.example.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import com.example.config.ScopeTime;
import com.example.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class ReaderTasklet implements Tasklet {
    private final UserRepository userRepository;
    private final ScopeTime stepScopeTime;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        log.info("stepScope={} stepExecutionContext={}",
                 stepScopeTime.getTime(), chunkContext.getStepContext().getStepExecutionContext());

        userRepository.findAll()
                      .forEach(user -> {
                          contribution.incrementReadCount();
                          log.info("{}", user);
                      });
        return RepeatStatus.FINISHED;
    }
}
