package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.client.JsonTestApiClient;
import com.example.model.DateTime;
import com.example.model.IpAddr;
import com.example.model.Md5;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ApiController {

    private final JsonTestApiClient jsonTestApiClient;

    @GetMapping("/ip")
    public IpAddr ip() {
        return jsonTestApiClient.getIpAddr();
    }

    @GetMapping("/date")
    public DateTime date() {
        return jsonTestApiClient.getDateTime();
    }

    @GetMapping("/md5")
    public Md5 md5(@RequestParam String text) {
        return jsonTestApiClient.getMd5(text);
    }
}
