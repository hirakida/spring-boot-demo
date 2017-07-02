package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    // argumentResolverを登録しているので、handler methodの引数にHttpRequestInfoが設定できる
    @GetMapping("/")
    public String index(HttpRequestInfo httpRequestInfo, Model model) {
        model.addAttribute("requestInfo", httpRequestInfo);
        model.addAttribute("cookies", httpRequestInfo.getCookies());
        return "index";
    }
}
