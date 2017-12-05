package com.example.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.bean.DemoBean;
import com.example.bean.DemoComponent;

@Controller
public class RootController {

    final DemoComponent demoComponent;
    final DemoBean demoBean1;
    final DemoBean demoBean2;

    public RootController(DemoComponent demoComponent,
                         DemoBean demoBean1,
                         @Qualifier("demoBean2") DemoBean demoBean2) {
        this.demoComponent = demoComponent;
        this.demoBean1 = demoBean1;
        this.demoBean2 = demoBean2;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("demoBean1", demoBean1);
        model.addAttribute("demoBean2", demoBean2);
        model.addAttribute("demoComponent", demoComponent);
        return "index";
    }
}
