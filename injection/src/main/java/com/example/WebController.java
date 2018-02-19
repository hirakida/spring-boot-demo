package com.example;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.bean.Bar;
import com.example.bean.Foo;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class WebController {

    // constructor injection
    private final Foo foo1;
    private final Foo foo2;

    // setter injection
    private Foo foo3;

    // field injection
    @Qualifier("bar1")
    @Autowired
    private Bar bar1;
    @Autowired
    private Bar bar2;

    public WebController(Foo foo1,
                         @Qualifier("foo2") Foo foo2) {
        this.foo1 = foo1;
        this.foo2 = foo2;
        log.info("constructor {} {} {} {} {}", this.foo1, this.foo2, foo3, bar1, bar2);
    }

    @Autowired
    public void setBaz(@Qualifier("foo3") Foo foo3) {
        this.foo3 = foo3;
        log.info("setBaz {} {} {} {} {}", foo1, foo2, foo3, bar1, bar2);
    }

    @PostConstruct
    public void init() {
        log.info("PostConstruct {} {} {} {} {}", foo1, foo2, foo3, bar1, bar2);
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("foo1", foo1);
        model.addAttribute("foo2", foo2);
        model.addAttribute("foo3", foo3);
        model.addAttribute("foo4", ApplicationContextHelper.getBeanFromXml(Foo.class));
        model.addAttribute("bar1", bar1);
        model.addAttribute("bar2", bar2);
        return "index";
    }
}
