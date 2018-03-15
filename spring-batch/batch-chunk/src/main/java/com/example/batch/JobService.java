package com.example.batch;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class JobService {

    private final JobLauncher jobLauncher;
    private final Job job1;

    public void job1() {
        Map<String, JobParameter> param = new HashMap<>();
        param.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters jobParameters = new JobParameters(param);
        try {
            log.info("##### {} start #####", job1.getName());
            jobLauncher.run(job1, jobParameters);
            log.info("##### {} end   #####", job1.getName());
        } catch (JobExecutionAlreadyRunningException |
                JobInstanceAlreadyCompleteException |
                JobParametersInvalidException |
                JobRestartException e) {
            log.error(e.toString());
        }
    }
}
