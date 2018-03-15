package com.example.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.MappedInterceptor;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.mvc.WebContentInterceptor;

import com.example.AppHandlerInterceptor;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    private final String[] includePatterns = { "/**" };
    private final String[] excludePatterns = { "/exclude", "/error", "/webjars/**" };

    //    @Bean
    public MappedInterceptor mappedInterceptor() {
        return new MappedInterceptor(includePatterns, excludePatterns, new AppHandlerInterceptor());
    }

    // resourceのpath毎にcacheSecondsを指定する場合に使用する
    @Bean
    public WebContentInterceptor webContentInterceptor() {
        WebContentInterceptor interceptor = new WebContentInterceptor();
        Properties properties = new Properties();
        properties.setProperty("/css/**", "0");
        properties.setProperty("/js/**", "0");
        interceptor.setCacheMappings(properties);
        return interceptor;
    }

    //    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");
        return interceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AppHandlerInterceptor())
                .addPathPatterns(includePatterns)
                .excludePathPatterns(excludePatterns);
        registry.addInterceptor(webContentInterceptor());
//        registry.addInterceptor(localeChangeInterceptor());
    }
}
