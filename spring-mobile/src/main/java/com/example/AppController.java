package com.example;

import org.springframework.mobile.device.Device;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class AppController {

    @GetMapping("/")
    public Device index(Device device) {
        log.info("isNormal={} isMobile={} isTablet={} DevicePlatform={}",
                 device.isNormal(), device.isMobile(), device.isTablet(), device.getDevicePlatform());
        return device;
    }
}
