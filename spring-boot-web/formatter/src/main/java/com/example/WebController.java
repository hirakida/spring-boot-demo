package com.example;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.enums.Blood;
import com.example.enums.Gender;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class WebController {

    @GetMapping("/")
    public String index(Model model) {
        return render(model);
    }

    /**
     * Formatter
     */
    @GetMapping(value = "/", params = "gender")
    public String index(Model model, @RequestParam Gender gender) {
        log.info("gender={}", gender);
        return render(model);
    }

    /**
     * Converter
     */
    @GetMapping(value = "/", params = "blood")
    public String index(Model model, @RequestParam Blood blood) {
        log.info("blood={}", blood);
        return render(model);
    }

    private static String render(Model model) {
        model.addAttribute("male", Gender.MALE);
        model.addAttribute("female", Gender.FEMALE);
        model.addAttribute("localDateTime", LocalDateTime.now());
        return "index";
    }
}
