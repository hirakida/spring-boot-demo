package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.SampleBean.ApplicationBean;
import com.example.SampleBean.PrototypeBean;
import com.example.SampleBean.RequestBean;
import com.example.SampleBean.SessionBean;
import com.example.SampleBean.SingletonBean;

@Controller
public class WebController {

    @Autowired
    LookupHelper lookupHelper;

    @Autowired
    PrototypeBean prototypeBean;

    @Autowired
    RequestBean requestBean;

    @Autowired
    SessionBean sessionBean;

    @Autowired
    ApplicationBean applicationBean;

    @Autowired
    SingletonBean singletonBean;

    @ModelAttribute
    public void model(Model model) {
//        prototypeBean = lookupHelper.getPrototypeBean();
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
    }

    @GetMapping("/")
    public String index() {
        // prototype, requestのcountは常に1
        // sessionはcookieがあればカウントアップ
        // application, singletonは常にカウントアップ
        return "index";
    }

    @GetMapping("/page2")
    public String page2() {
        return "index";
    }


    @GetMapping("/redirect")
    public String redirect() {
        return "redirect:/";
    }
}
