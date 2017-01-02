package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ApiController {

    @Autowired
    SampleService sampleService;

    @GetMapping("/{id}")
    public String get(@PathVariable long id) {
        log.info("controller start id={}", id);
        String result = sampleService.retry(id);
        log.info("controller end id={} result={}", id, result);
        return result;
    }
}
