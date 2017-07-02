package com.example;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.domain.AccountRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@AllArgsConstructor
@Slf4j
public class Application implements CommandLineRunner {

    final AccountRepository accountRepository;
    final JobLauncher jobLauncher;
    final Job job;

    @Override
    public void run(String... var1) throws Exception {
        log.info("##### batch start #####");
        log.info("{}", accountRepository.findAll());

        Map<String, JobParameter> param = new HashMap<>();
        param.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters jobParameters = new JobParameters(param);
        jobLauncher.run(job, jobParameters);

        log.info("{}", accountRepository.findAll());
        log.info("##### batch end #####");
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
