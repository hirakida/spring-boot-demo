
https://docs.spring.io/spring-boot/docs/2.7.x/reference/html/howto.html#howto.webserver.configure-ssl

```
$ keytool -genkey -alias tomcat -storetype PKCS12 -keyalg RSA -keystore keystore.p12

$ curl -v -k https://localhost:8443
```
