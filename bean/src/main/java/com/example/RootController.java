package com.example;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.bean.DemoBean;
import com.example.bean.DemoComponent;
import com.example.config.LazyBean;

@Controller
public class RootController {

    private final DemoComponent demoComponent;
    private final DemoBean demoBean1;
    private final DemoBean demoBean2;
    private final DemoBean demoBean;    //demoBean1
    private final DemoBean lazyBean;    //demoBean2

    public RootController(DemoComponent demoComponent,
                          DemoBean demoBean1,
                          @Qualifier("demoBean2") DemoBean demoBean2,
                          DemoBean demoBean,
                          @LazyBean DemoBean lazyBean) {
        this.demoComponent = demoComponent;
        this.demoBean1 = demoBean1;
        this.demoBean2 = demoBean2;
        this.demoBean = demoBean;
        this.lazyBean = lazyBean;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("demoBean", demoBean);
        model.addAttribute("lazyBean", lazyBean);
        model.addAttribute("demoBean1", demoBean1);
        model.addAttribute("demoBean2", demoBean2);
        model.addAttribute("demoComponent", demoComponent);
        return "index";
    }
}
