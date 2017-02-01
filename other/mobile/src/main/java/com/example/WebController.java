package com.example;

import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String index(Device device, Model model) {
        model.addAttribute("isNormal", device.isNormal());
        model.addAttribute("isMobile", device.isMobile());
        model.addAttribute("isTablet", device.isTablet());
        model.addAttribute("DevicePlatform", device.getDevicePlatform());
        return "index";
    }
}
