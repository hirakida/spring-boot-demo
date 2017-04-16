package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@AllArgsConstructor
@Slf4j
public class AppController {

    final MetricsService metricsService;

    @GetMapping("/")
    public String index() {
        return "redirect:/manage";
    }

    @GetMapping("/increment")
    @ResponseBody
    public void increment() {
        metricsService.increment();
    }

    @GetMapping("/decrement")
    @ResponseBody
    public void decrement() {
        metricsService.decrement();
    }

    @GetMapping("/reset")
    @ResponseBody
    public void reset() {
        metricsService.reset();
    }

    @GetMapping("/gauge")
    @ResponseBody
    public void gauge(@RequestParam long value) {
        metricsService.gauge(value);
    }
}
