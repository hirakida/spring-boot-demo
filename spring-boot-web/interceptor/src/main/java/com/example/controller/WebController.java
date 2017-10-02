package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.exception.DataNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class WebController {

    @GetMapping("/")
    public String index(Model model, @RequestParam(defaultValue = "1") long id) {
        log.info("id: {}", id);
        if (id > 10) {
            throw new DataNotFoundException("not found " + id);
        }
        model.addAttribute("message", "index");
        return "index";
    }

    /**
     * AppHandlerInterceptor's exclude path pattern
     */
    @GetMapping("/exclude")
    public String exclude(Model model) {
        model.addAttribute("message", "exclude");
        return "index";
    }
}
