package com.example;

import org.springframework.boot.ExitCodeGenerator;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Component
public class ExitCodeGeneratorImpl implements ExitCodeGenerator {

    @RequiredArgsConstructor
    public enum ExitCode {
        EXIT_SUCCESS(0),
        EXIT_FAILURE(-1);
        @Getter
        private final int code;
    }

    private int exitCode;

    public ExitCodeGeneratorImpl() {
        this.exitCode = ExitCode.EXIT_SUCCESS.code;
    }

    @Override
    public int getExitCode() {
        return exitCode;
    }

    public void setExitCode(ExitCode exitCode) {
        this.exitCode = exitCode.getCode();
    }
}
