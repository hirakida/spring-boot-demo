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
                         @Value("${app.string.value2:default1}") String str2,
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
        return render(model, "@ConfigurationProperties",
                      properties.getString().getValue1(),
                      properties.getString().getValue2(),
                      properties.getNumber().getValue1(),
                      properties.getNumber().getValue2());
    }

    @GetMapping("/value")
    public String value(Model model) {
        return render(model, "@Value", str1, str2, number1, number2);
    }

    @GetMapping("/environment")
    public String environment(Model model) {
        String str1 = environment.getProperty("app.string.value1");
        String str2 = environment.getProperty("app.string.value2", String.class, "default2");
        long number1 = environment.getProperty("app.number.value1", Long.class);
        long number2 = environment.getProperty("app.number.value2", Long.class, 0L);
        return render(model, "Environment", str1, str2, number1, number2);
    }

    private static String render(Model model, String type,
                                 String str1, String str2,
                                 long number1, long number2) {
        model.addAttribute("type", type);
        model.addAttribute("str1", str1);
        model.addAttribute("str2", str2);
        model.addAttribute("number1", number1);
        model.addAttribute("number2", number2);
        return "index";
    }
}
