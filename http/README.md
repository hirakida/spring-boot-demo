## SSL
https://docs.spring.io/spring-boot/docs/3.0.x/reference/html/howto.html#howto.webserver.configure-ssl

```
% keytool -genkey -alias tomcat -storetype PKCS12 -keyalg RSA -keystore keystore.p12
or
% mkcert localhost
```

## HTTP/2
https://docs.spring.io/spring-boot/docs/3.0.x/reference/html/howto.html#howto.webserver.configure-http2  
https://docs.spring.io/spring-framework/docs/5.3.x/reference/html/web.html#mvc-http2  

h2c
```
% curl -v -k --http2 http://localhost:8080/
% curl -v -k --http2-prior-knowledge http://localhost:8080/
```

## CORS
https://docs.spring.io/spring-framework/docs/5.3.x/reference/html/web.html#mvc-cors

OK: http://localhost:8080/index.html  
NG: http://127.0.0.1:8080/index.html

## HTTP Caching
https://docs.spring.io/spring-framework/docs/5.3.x/reference/html/web.html#mvc-caching  
