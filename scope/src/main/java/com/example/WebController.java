package com.example;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.bean.ApplicationBean;
import com.example.bean.Prototype1Bean;
import com.example.bean.Prototype2Bean;
import com.example.bean.RequestBean;
import com.example.bean.SessionBean;
import com.example.bean.SingletonBean;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class WebController {

    private final Prototype1Bean prototype1Bean;
    private final RequestBean requestBean;
    private final SessionBean sessionBean;
    private final ApplicationBean applicationBean;
    private final SingletonBean singletonBean;

    @GetMapping("/")
    public String index(Model model) {
        prototype1Bean.setId(1);
        prototype1Bean.setName("prototype1");
        Prototype2Bean prototype2Bean = getPrototype2Bean();
        prototype2Bean.setId(1);
        prototype2Bean.setName("prototype2");

        requestBean.setId(2);
        requestBean.setName("request");

        sessionBean.setId(3);
        sessionBean.setName("session");

        applicationBean.setId(4);
        applicationBean.setName("application");
        singletonBean.setId(5);
        singletonBean.setName("singleton");

        model.addAttribute("prototype1Bean", prototype1Bean);
        model.addAttribute("prototype2Bean", prototype2Bean);
        model.addAttribute("requestBean", requestBean);
        model.addAttribute("sessionBean", sessionBean);
        model.addAttribute("appBean", applicationBean);
        model.addAttribute("singletonBean", singletonBean);
        return "index";
    }

    @Lookup
    public Prototype2Bean getPrototype2Bean() {
        // return value is dummy.
        return null;
    }
}
