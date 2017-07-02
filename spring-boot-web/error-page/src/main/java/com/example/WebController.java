package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.exception.DataNotFoundException;
import com.example.exception.ForbiddenException;
import com.example.exception.ServerErrorException;

@Controller
public class WebController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("message", "error page sample");
        return "index";
    }

    @GetMapping("/403")
    public String forbidden() {
        throw new ForbiddenException("forbidden exception");
    }

    @GetMapping("/404")
    public String notFound() {
        throw new DataNotFoundException("not found exception");
    }

    @GetMapping("/500")
    public String serverError() {
        throw new ServerErrorException("server error exception");
    }
}
