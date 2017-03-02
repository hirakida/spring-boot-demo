package com.example;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // viewを直接指定できる
        registry.addViewController("404__")
                .setViewName("error/404");
        // /redirectにアクセスされた場合は、リダイレクトする
        registry.addRedirectViewController("redirect", "/");
        // /faviconにアクセスされた場合は、ステータスコードを返却する
        registry.addStatusController("favicon", HttpStatus.NO_CONTENT);
    }
}
