package com.example;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bean.DemoBean;
import com.example.bean.LifecycleBean;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class DemoController {
    private final LifecycleBean lifecycleBean;
    private final DemoBean demoBean;

    @GetMapping("/")
    public Map<String, Object> index() {
        return Map.of("lifecycleBean", lifecycleBean,
                      "demoBean", demoBean);
    }
}
