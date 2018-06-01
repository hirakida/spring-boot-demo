package com.example;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.bean.ApplicationBean;
import com.example.bean.PrototypeBean;
import com.example.bean.RequestBean;
import com.example.bean.SessionBean;
import com.example.bean.SingletonBean;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class WebController {

    private final RequestBean requestBean;
    private final SessionBean sessionBean;
    private final ApplicationBean applicationBean;
    private final SingletonBean singletonBean;

    @GetMapping("/")
    public String index(Model model) {
        PrototypeBean prototypeBean = getPrototypeBean();
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

    @Lookup
    public PrototypeBean getPrototypeBean() {
        // return value is dummy.
        return null;
    }
}
