package com.example.web;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.web.enums.Blood;
import com.example.web.enums.Gender;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class WebController {

    @GetMapping("/")
    public String index(@RequestParam Optional<Gender> gender,
                        @RequestParam Optional<Blood> blood,
                        @RequestParam Optional<LocalDateTime> datetime,
                        Model model) {
        log.info("{} {} {}", gender, blood, datetime);
        model.addAttribute("male", Gender.MALE);
        model.addAttribute("female", Gender.FEMALE);
        model.addAttribute("localDateTime", LocalDateTime.now());
        return "index";
    }
}
