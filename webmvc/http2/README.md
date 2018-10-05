
https://docs.spring.io/spring-boot/docs/current/reference/html/howto-embedded-web-servers.html#howto-configure-http2

```
$ keytool -genkey -alias http2 -storetype PKCS12 -keyalg RSA -keystore keystore.p12
```

```
$ curl -v -k --http2 https://localhost:8443/
```
