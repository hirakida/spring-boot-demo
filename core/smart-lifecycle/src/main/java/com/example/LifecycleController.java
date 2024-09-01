package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LifecycleController {
    private final MyLifecycle myLifecycle;

    public LifecycleController(MyLifecycle myLifecycle) {
        this.myLifecycle = myLifecycle;
    }

    @GetMapping("/running")
    public boolean isRunning() {
        return myLifecycle.isRunning();
    }
}
