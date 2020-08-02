package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LifecycleController {
    private final MyLifecycle myLifecycle;

    @GetMapping("/running")
    public boolean isRunning() {
        return myLifecycle.isRunning();
    }

    @GetMapping("/phase")
    public int getPhase() {
        return myLifecycle.getPhase();
    }
}
