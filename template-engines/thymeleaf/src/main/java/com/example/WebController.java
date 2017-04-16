package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Controller
public class WebController {

    @Autowired
    RequestMappingHandlerMapping requestMappingHandlerMapping;

    @GetMapping("/")
    public String index(Model model) {
        String text = "<b>text</b>";
        model.addAttribute("text", text);
        return "index";
    }
}
