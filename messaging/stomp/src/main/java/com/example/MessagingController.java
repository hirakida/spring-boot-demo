package com.example;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@AllArgsConstructor
@Slf4j
public class MessagingController {

    final SimpMessagingTemplate simpMessagingTemplate;

    @GetMapping("/")
    public String index(Model model) {
        long id = 1;
        model.addAttribute("endpointUrl", "ws://localhost:8080/endpoint");
        model.addAttribute("subscribe1", "/topic/greetings");
        model.addAttribute("subscribe2", "/topic/from_api/" + id);
        model.addAttribute("subscribe3", "/topic/room/" + id);
        model.addAttribute("greetingsPath", "/app/hello");
        model.addAttribute("messagePath", "/app/room/" + id);
        return "index";
    }

    /**
     * messageを受け取ってからmessage brokerに渡す
     */
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public String greetings(String name) {
        log.info("greetings={}", name);
        // この戻り値を/topicに渡す
        return "Hello " + name;
    }

    @SubscribeMapping("/room/{id}")
    public void subscribe(@DestinationVariable String id, String message,
                          SimpMessageHeaderAccessor headerAccessor) {
        log.info("subscribe id={} message={} headerAccessor={}", id, message, headerAccessor);
        simpMessagingTemplate.convertAndSend("/topic/room/" + id, message);
    }
}
