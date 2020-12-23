package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {
    private static final long ROOM_ID = 1;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("roomId", ROOM_ID);
        return "index";
    }
}
