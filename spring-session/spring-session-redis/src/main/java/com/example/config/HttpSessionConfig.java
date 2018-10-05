package com.example.config;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.web.servlet.server.Session.Cookie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.web.context.annotation.SessionScope;

import com.example.bean.SessionBean;

@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 60)
public class HttpSessionConfig {

    @Bean
    public CookieSerializer cookieSerializer(ServerProperties serverProperties) {
        Cookie cookie = serverProperties.getServlet().getSession().getCookie();

        // https://github.com/spring-projects/spring-session/blob/2.0.6.RELEASE/spring-session-core/src/main/java/org/springframework/session/config/annotation/web/http/SpringHttpSessionConfiguration.java#L166
        DefaultCookieSerializer serializer = new DefaultCookieSerializer();
        if (cookie.getName() != null) {
            serializer.setCookieName(cookie.getName());
        }
        if (cookie.getDomain() != null) {
            serializer.setDomainName(cookie.getDomain());
        }
        if (cookie.getPath() != null) {
            serializer.setCookiePath(cookie.getPath());
        }
        if (cookie.getMaxAge() != null) {
            serializer.setCookieMaxAge((int) cookie.getMaxAge().getSeconds());
        }
        if (cookie.getSecure() != null) {
            serializer.setUseSecureCookie(cookie.getSecure());
        }
        if (cookie.getHttpOnly() != null) {
            serializer.setUseHttpOnlyCookie(cookie.getHttpOnly());
        }
        return serializer;
    }

    @Bean
    @SessionScope
    public SessionBean sessionBean() {
        return new SessionBean();
    }
}
