package com.example.batch;

import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.core.MemberRepository;
import com.example.core.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class CronTasks {
    private final JobLauncher jobLauncher;
    private final Job job1;
    private final UserRepository userRepository;
    private final MemberRepository memberRepository;

    @Scheduled(fixedRate = 10000)
    public void job1() {
        runJob(job1);
        userRepository.findAll()
                      .forEach(user -> log.info("{}", user));
        memberRepository.findAll()
                        .forEach(member -> log.info("{}", member));
    }

    private void runJob(Job job) {
        Map<String, JobParameter> params = Map.of("time", new JobParameter(System.currentTimeMillis()));
        JobParameters jobParameters = new JobParameters(params);
        try {
            log.info("##### {} start #####", job.getName());
            jobLauncher.run(job, jobParameters);
            log.info("##### {} end   #####", job.getName());
        } catch (JobExecutionAlreadyRunningException |
                JobInstanceAlreadyCompleteException |
                JobParametersInvalidException |
                JobRestartException e) {
            log.error(e.getMessage(), e);
        }
    }
}
