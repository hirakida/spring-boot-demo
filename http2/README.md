
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
