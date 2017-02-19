package com.example;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.bean.Bar;
import com.example.bean.Baz;
import com.example.bean.ComponentBean;
import com.example.bean.Foo;
import com.example.bean.Qux;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class WebController {

    // constructor injection
    final ApplicationContext context;
    final Foo foo;
    final ComponentBean componentBean;

    // field injection
    @Qualifier("bar1")
    @Autowired
    Bar bar;

    // setter injection
    Baz baz;

    // Spring 4.3からはconstructorに@Autowiredを付けなくてよくなった
    public WebController(ApplicationContext context,
                         @Qualifier("foo1") Foo foo,
                         ComponentBean componentBean) {
        this.context = context;
        this.foo = foo;
        this.componentBean = componentBean;
        log.info("constructor {} {} {}", this.foo, this.bar, this.baz);
    }

    // 全てinjectionされた後で呼ばれる
    @PostConstruct
    public void init(){
        log.info("PostConstruct {} {} {}", foo, bar, baz);
    }

    @Autowired
    public void setBaz(Baz baz) {
        this.baz = baz;
        log.info("setBaz {} {} {}", this.foo, this.bar, this.baz);
    }

    @GetMapping("/")
    public String index(Model model) {
        // fieldにinjectionされたbeanを使用
        model.addAttribute("foo", foo);
        model.addAttribute("bar", bar);
        model.addAttribute("baz", baz);
        model.addAttribute("componentBean", componentBean);
        return "index";
    }

    @GetMapping("/config")
    public String config(Model model) {
        // Java configから取得
        model.addAttribute("foo", context.getBean("foo2", Foo.class));
        model.addAttribute("bar", context.getBean(Bar.class));  // Primaryのbar2
        model.addAttribute("baz", context.getBean(Baz.class));
        model.addAttribute("qux", context.getBean(Qux.class));
        model.addAttribute("componentBean", componentBean);
        return "index";
    }
}
