
## HTTP/2
https://docs.spring.io/spring-boot/docs/2.7.x/reference/html/howto.html#howto.webserver.configure-http2  

h2
```
% keytool -genkey -alias tomcat -storetype PKCS12 -keyalg RSA -keystore keystore.p12
```
```
% curl -v -k --http2 https://localhost:8443/
```

h2c
```
% curl -v -k --http2 http://localhost:8080/
```

## HTTPS
https://docs.spring.io/spring-boot/docs/2.7.x/reference/html/howto.html#howto.webserver.configure-ssl

```
$ keytool -genkey -alias tomcat -storetype PKCS12 -keyalg RSA -keystore keystore.p12

$ curl -v -k https://localhost:8443
```

## HTTP Caching
https://docs.spring.io/spring-framework/docs/5.3.x/reference/html/web.html#mvc-caching  
