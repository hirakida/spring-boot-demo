package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bean.DemoBean;
import com.example.bean.LifecycleBean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ApiController {
    private final LifecycleBean lifecycleBean;
    private final DemoBean demoBean;

    @GetMapping("/")
    public Response index() {
        return new Response(lifecycleBean, demoBean);
    }

    @Data
    @AllArgsConstructor
    public static class Response {
        private LifecycleBean lifecycleBean;
        private DemoBean demoBean;
    }
}
