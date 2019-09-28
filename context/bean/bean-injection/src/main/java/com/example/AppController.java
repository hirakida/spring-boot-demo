package com.example;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.bean.Bar;
import com.example.bean.Foo;
import com.example.bean.SecondaryBean;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class AppController {
    private final ApplicationContext configContext;
    private final ApplicationContext xmlContext;

    // Constructor injection
    private final Foo foo;
    private final Foo foo1;
    private final Foo foo2;

    // Setter injection
    private Foo foo3;

    // Field injection
    @Qualifier("bar1")
    @Autowired
    private Bar bar1;
    @Autowired
    private Bar bar2;

    public AppController(Foo foo, Foo foo1, @Qualifier("foo2") Foo foo2) {
        configContext = new AnnotationConfigApplicationContext(BeanConfig.class);
        xmlContext = new ClassPathXmlApplicationContext("bean.xml");
        this.foo = foo;
        this.foo1 = foo1;
        this.foo2 = foo2;
        log("constructor");
    }

    @Autowired
    public void setBaz(@SecondaryBean Foo foo3) {
        this.foo3 = foo3;
        log("setBaz");
    }

    @PostConstruct
    public void init() {
        log("PostConstruct");
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("foo", foo);
        model.addAttribute("foo1", foo1);
        model.addAttribute("foo2", foo2);
        model.addAttribute("foo3", foo3);
        model.addAttribute("foo4", configContext.getBean("foo4"));
        model.addAttribute("foo5", xmlContext.getBean(Foo.class));
        model.addAttribute("bar1", bar1);
        model.addAttribute("bar2", bar2);
        return "index";
    }

    private void log(String msg) {
        log.info("{} foo={} foo1={} foo2={} foo3={} bar1={} bar2={}", msg, foo, foo1, foo2, foo3, bar1, bar2);
    }
}
