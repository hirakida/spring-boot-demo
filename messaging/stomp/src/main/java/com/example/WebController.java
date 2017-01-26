package com.example;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class WebController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("endpointUrl", "ws://localhost:8080/endpoint");
        model.addAttribute("subscribePath", "/topic/greetings");
        model.addAttribute("messagePath", "/app/hello");
        return "index";
    }

    // messageを受け取る
    @MessageMapping("/hello")
    @SendTo("/topic/greetings") // 処理結果の送り先
    public String hello(String name) {
        log.info("received {}", name);
        return "Hello " + name;
    }
}
