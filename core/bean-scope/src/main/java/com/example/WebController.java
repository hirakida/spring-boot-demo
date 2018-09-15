package com.example;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.bean.PrototypeBean;
import com.example.bean.ScopeBean;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class WebController {

    private final ScopeBean requestBean;
    private final ScopeBean sessionBean;
    private final ScopeBean applicationBean;
    private final ScopeBean singletonBean;

    @GetMapping("/")
    public String index(Model model) {
        requestBean.setId(2);
        requestBean.setName("request");
        sessionBean.setId(3);
        sessionBean.setName("session");
        applicationBean.setId(4);
        applicationBean.setName("application");
        singletonBean.setId(5);
        singletonBean.setName("singleton");

        PrototypeBean prototypeBean = getPrototypeBean();
        prototypeBean.setId(1);
        prototypeBean.setName("prototype");

        model.addAttribute("prototypeBean", prototypeBean);
        model.addAttribute("requestBean", requestBean);
        model.addAttribute("sessionBean", sessionBean);
        model.addAttribute("appBean", applicationBean);
        model.addAttribute("singletonBean", singletonBean);
        return "index";
    }

    @Lookup
    public PrototypeBean getPrototypeBean() {
        return null;
    }
}
