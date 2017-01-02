package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class ApiController {

    private final SampleService sampleService;

    @GetMapping("/")
    public String run() {
        log.info("WebController::run start");
        sampleService.run();
        log.info("WebController::run end");
        return "ok";
    }

    @GetMapping("async_error")
    public String asyncError() {
        log.info("WebController::asyncError start");
        sampleService.error();
        log.info("WebController::asyncError end");
        return "ok";
    }
}
