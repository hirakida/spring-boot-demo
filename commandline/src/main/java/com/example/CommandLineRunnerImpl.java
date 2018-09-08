package com.example;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.ExitCodeGeneratorImpl.ExitCode;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final ExitCodeGeneratorImpl exitCodeGenerator;

    @Override
    public void run(String... args) {
        log.info("{}", Arrays.toString(args));
        if (args.length == 0) {
            exitCodeGenerator.setExitCode(ExitCode.EXIT_FAILURE);
            return;
        }

        System.out.println(Arrays.toString(args));
        exitCodeGenerator.setExitCode(ExitCode.EXIT_SUCCESS);
    }
}
