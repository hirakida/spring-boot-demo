package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.bean.DemoBean;
import com.example.bean.DemoComponent;
import com.example.config.LazyBean;

@Controller
@RequestMapping("/demo2")
public class Demo2Controller {

    final DemoComponent demoComponent;
    final DemoBean demoBean;    //demoBean2

    public Demo2Controller(DemoComponent demoComponent,
                          @LazyBean DemoBean demoBean) {
        this.demoComponent = demoComponent;
        this.demoBean = demoBean;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("demoBean", demoBean);
        model.addAttribute("demoComponent", demoComponent);
        return "index";
    }
}
