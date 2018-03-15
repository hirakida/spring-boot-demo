package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    private final String str1;
    private final String str2;
    private final long number1;
    private final long number2;
    private final Environment environment;
    private final AppProperties properties;

    public WebController(@Value("${app.string.value1}") String str1,
                         @Value("${app.string.value2:default}") String str2,
                         @Value("${app.number.value1}") long number1,
                         @Value("${app.number.value2}") long number2,
                         Environment environment,
                         AppProperties properties) {
        this.str1 = str1;
        this.str2 = str2;
        this.number1 = number1;
        this.number2 = number2;
        this.environment = environment;
        this.properties = properties;
    }

    @GetMapping("/")
    public String index(Model model) {
        // use ConfigurationProperties
        model.addAttribute("type", "ConfigurationProperties");
        model.addAttribute("str1", properties.getString().getValue1());
        model.addAttribute("str2", properties.getString().getValue2());
        model.addAttribute("number1", properties.getNumber().getValue1());
        model.addAttribute("number2", properties.getNumber().getValue2());
        return "index";
    }

    @GetMapping("/value")
    public String value(Model model) {
        // use @Value
        model.addAttribute("type", "@Value");
        model.addAttribute("str1", str1);
        model.addAttribute("str2", str2);
        model.addAttribute("number1", number1);
        model.addAttribute("number2", number2);
        return "index";
    }

    @GetMapping("/environment")
    public String environment(Model model) {
        // use Environment
        String str1 = environment.getProperty("app.string.value1");
        String str2 = environment.getProperty("app.string.value2", String.class, "default value");
        long number1 = environment.getProperty("app.number.value1", Long.class);
        long number2 = environment.getProperty("app.number.value2", Long.class, 0L);
        model.addAttribute("type", "Environment");
        model.addAttribute("str1", str1);
        model.addAttribute("str2", str2);
        model.addAttribute("number1", number1);
        model.addAttribute("number2", number2);
        return "index";
    }
}
