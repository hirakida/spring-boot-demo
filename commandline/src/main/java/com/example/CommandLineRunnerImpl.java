package com.example;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner, ExitCodeGenerator {
    private int exitCode = ExitCode.EXIT_SUCCESS.code;

    @RequiredArgsConstructor
    private enum ExitCode {
        EXIT_SUCCESS(0),
        EXIT_FAILURE(-1);
        private final int code;
    }

    @Override
    public void run(String... args) {
        System.out.println("args: " + Arrays.toString(args));
        if (args.length == 0) {
            exitCode = ExitCode.EXIT_FAILURE.code;
            return;
        }
        System.out.println(LocalDateTime.now());
    }

    @Override
    public int getExitCode() {
        return exitCode;
    }
}
