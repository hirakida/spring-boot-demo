package com.example;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.bean.MyBean;
import com.example.bean.PrototypeBean;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class DemoController {
    private final MyBean requestBean;
    private final MyBean sessionBean;
    private final MyBean applicationBean;
    private final MyBean singletonBean;
    private final ApplicationContext context;

    @GetMapping("/")
    public String index(Model model) {
        PrototypeBean prototypeBean = context.getBean(PrototypeBean.class);
        prototypeBean.setId(1);
        prototypeBean.setName("prototype");

        requestBean.setId(2);
        requestBean.setName("request");
        sessionBean.setId(3);
        sessionBean.setName("session");
        applicationBean.setId(4);
        applicationBean.setName("application");
        singletonBean.setId(5);
        singletonBean.setName("singleton");

        model.addAttribute("prototypeBean", prototypeBean);
        model.addAttribute("requestBean", requestBean);
        model.addAttribute("sessionBean", sessionBean);
        model.addAttribute("appBean", applicationBean);
        model.addAttribute("singletonBean", singletonBean);
        return "index";
    }
}
