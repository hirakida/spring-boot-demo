package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WebController {

    @RequestMapping(path = "/echo", method = RequestMethod.GET)
    public String echo() {
        return "echo";
    }

    @RequestMapping(path = "/random", method = RequestMethod.GET)
    public String random() {
        return "random";
    }
}
