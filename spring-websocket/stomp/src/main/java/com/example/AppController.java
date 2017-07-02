package com.example;

import static com.example.StompConfig.APP_DESTINATION_PREFIXES;
import static com.example.StompConfig.ENDPOINT_PATH;
import static com.example.StompConfig.TOPIC_DESTINATION_PREFIXES;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@AllArgsConstructor
@Slf4j
public class AppController {

    final SimpMessagingTemplate simpMessagingTemplate;

    @GetMapping("/")
    public String index(Model model) {
        long id = 1;
        model.addAttribute("endpointUrl", "ws://localhost:8080/" + ENDPOINT_PATH);
        model.addAttribute("subscribe", TOPIC_DESTINATION_PREFIXES + "/room/" + id);
        model.addAttribute("appSubscribe", APP_DESTINATION_PREFIXES + "/subscribe/room/" + id);
        model.addAttribute("appMessage", APP_DESTINATION_PREFIXES + "/message/room/" + id);
        return "index";
    }

    @GetMapping("/api/room/{id}")
    @ResponseBody
    public void sendMessage(@PathVariable Long id, @RequestParam String message) {
        log.info("api id={} message={}", id, message);
        simpMessagingTemplate.convertAndSend("/topic/room/" + id, message);
    }

    @SubscribeMapping("/subscribe/room/{id}")
    public void subscribe(@DestinationVariable String id, String message,
                          SimpMessageHeaderAccessor headerAccessor) {
        log.info("subscribe id={} message={} headerAccessor={}", id, message, headerAccessor);
        simpMessagingTemplate.convertAndSend("/topic/room/" + id, message);
    }

    /**
     * messageを受け取ってからmessage brokerに渡す
     */
    @MessageMapping("/message/room/{id}")
    @SendTo("/topic/room/{id}")
    public String message(@DestinationVariable String id, String message) {
        log.info("message={}", message);
        // この戻り値を/topicに渡す
        return message;
    }
}
