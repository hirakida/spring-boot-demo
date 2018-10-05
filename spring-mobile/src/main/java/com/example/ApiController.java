package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mobile.device.Device;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    private static final Logger logger = LoggerFactory.getLogger(ApiController.class);

    @GetMapping("/")
    public Device index(Device device) {
        logger.info("isNormal={} isMobile={} isTablet={} DevicePlatform={}",
                    device.isNormal(), device.isMobile(), device.isTablet(), device.getDevicePlatform());
        return device;
    }
}
