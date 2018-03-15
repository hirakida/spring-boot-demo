package com.example;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.bean.LifecycleImpl;
import com.example.bean.POJOBean;
import com.example.bean.BeanImpl;
import com.example.config.LazyBean;

@Controller
public class WebController {

    private final BeanImpl beanImpl;
    private final LifecycleImpl lifecycle;
    private final POJOBean pojoBean1;
    private final POJOBean pojoBean2;
    private final POJOBean pojoBean;    //pojoBean1
    private final POJOBean lazyBean;    //pojoBean2

    public WebController(BeanImpl beanImpl,
                         LifecycleImpl lifecycle,
                         POJOBean pojoBean1,
                         @Qualifier("pojoBean2") POJOBean pojoBean2,
                         POJOBean pojoBean,
                         @LazyBean POJOBean lazyBean) {
        this.beanImpl = beanImpl;
        this.lifecycle = lifecycle;
        this.pojoBean1 = pojoBean1;
        this.pojoBean2 = pojoBean2;
        this.pojoBean = pojoBean;
        this.lazyBean = lazyBean;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("pojoBean1", pojoBean1);
        model.addAttribute("pojoBean2", pojoBean2);
        model.addAttribute("pojoBean", pojoBean);
        model.addAttribute("lazyBean", lazyBean);
        model.addAttribute("beanImpl", beanImpl);
        model.addAttribute("lifecycle", lifecycle);
        return "index";
    }
}
