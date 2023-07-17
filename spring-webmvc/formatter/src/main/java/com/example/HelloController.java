package com.example;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.enums.Blood;
import com.example.enums.Gender;

@Controller
public class HelloController {
    @GetMapping("/")
    public String index(@RequestParam Optional<Gender> gender,
                        @RequestParam Optional<Blood> blood,
                        @RequestParam Optional<LocalDateTime> datetime,
                        Model model) {
        model.addAttribute("gender", gender.orElse(Gender.MALE));
        model.addAttribute("datetime", datetime.orElse(LocalDateTime.now()));
        return "index";
    }
}
