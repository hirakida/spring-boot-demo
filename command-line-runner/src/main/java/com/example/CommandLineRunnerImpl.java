package com.example;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.stereotype.Component;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner, ExitCodeGenerator {
    private static final int EXIT_SUCCESS = 0;
    private static final int EXIT_FAILURE = -1;
    private int exitCode = EXIT_SUCCESS;

    @Override
    public void run(String... args) {
        if (args.length == 0) {
            exitCode = EXIT_FAILURE;
            return;
        }

        System.out.println("args: " + Arrays.toString(args));
        System.out.println(LocalDateTime.now());
    }

    @Override
    public int getExitCode() {
        return exitCode;
    }
}
