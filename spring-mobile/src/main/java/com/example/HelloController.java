package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mobile.device.Device;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    private static final Logger log = LoggerFactory.getLogger(HelloController.class);

    @GetMapping("/")
    public Device hello(Device device) {
        log.info("isNormal={} isMobile={} isTablet={} DevicePlatform={}",
                 device.isNormal(), device.isMobile(), device.isTablet(), device.getDevicePlatform());
        return device;
    }
}
