package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.client.DateApiClient;
import com.example.client.IpApiClient;
import com.example.client.Md5ApiClient;
import com.example.client.model.DateTime;
import com.example.client.model.IpAddr;
import com.example.client.model.Md5;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ApiController {
    private final IpApiClient ipApiClient;
    private final DateApiClient dateApiClient;
    private final Md5ApiClient md5ApiClient;

    @GetMapping("/ip")
    public IpAddr getIpAddr() {
        return ipApiClient.getIp();
    }

    @GetMapping("/date")
    public DateTime getDateTime() {
        return dateApiClient.getDate();
    }

    @GetMapping("/md5")
    public Md5 getMd5(@RequestParam String text) {
        return md5ApiClient.getMd5(text);
    }
}
