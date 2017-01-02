package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.ant("/api/**"))
                .build()
                .apiInfo(apiInfo());
    }

    private static ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("ApiInfo title")
                .description("ApiInfo description")
                .version("1.0")
                .licenseUrl("http://localhost:8080/license")
                .termsOfServiceUrl("http://localhost:8080/terms")
                .build();
    }
}
