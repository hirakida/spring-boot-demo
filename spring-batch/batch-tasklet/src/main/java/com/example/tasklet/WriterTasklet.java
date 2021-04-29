package com.example.tasklet;

import java.util.Random;
import java.util.stream.IntStream;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import com.example.config.BatchConfig.ScopeTime;
import com.example.entity.User;
import com.example.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class WriterTasklet implements Tasklet {
    private final UserRepository userRepository;
    private final ScopeTime jobScopeTime;
    private final ScopeTime stepScopeTime;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        log.info("jobScope={} stepScope={}", jobScopeTime.getTime(), stepScopeTime.getTime());

        IntStream.rangeClosed(1, 5)
                 .forEach(i -> {
                     User user = new User();
                     user.setName("user" + new Random().nextInt());
                     userRepository.saveAndFlush(user);

                     contribution.incrementWriteCount(1);
                 });

        return RepeatStatus.FINISHED;
    }
}
