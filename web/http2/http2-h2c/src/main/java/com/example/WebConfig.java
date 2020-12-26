package com.example;

import org.apache.coyote.http2.Http2Protocol;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {
    @Bean
    public TomcatConnectorCustomizer connectorCustomizer() {
        return connector -> connector.addUpgradeProtocol(new Http2Protocol());
    }
}
