
https://docs.spring.io/spring-boot/docs/2.4.x/reference/html/howto.html#howto-configure-http2  

```
$ keytool -genkey -alias tomcat -storetype PKCS12 -keyalg RSA -keystore keystore.p12
```

h2
```
% curl -v -k --http2 https://localhost:8443/
```

h2c
```
% curl -v -k --http2 http://localhost:8080/
```
