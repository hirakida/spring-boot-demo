package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.bean.DemoBean;
import com.example.bean.DemoComponent;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/demo1")
@AllArgsConstructor
public class Demo1Controller {

    final DemoComponent demoComponent;
    final DemoBean demoBean;    //demoBean1

    @GetMapping
    public String index(Model model) {
        model.addAttribute("demoBean", demoBean);
        model.addAttribute("demoComponent", demoComponent);
        return "index";
    }
}
