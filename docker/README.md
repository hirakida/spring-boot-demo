
https://docs.spring.io/spring-boot/docs/3.2.x/gradle-plugin/reference/htmlsingle/#build-image  

```
$ echo $GHCR_TOKEN | docker login ghcr.io -u hirakida --password-stdin

$ ./gradlew bootBuildImage

$ docker run --rm -e "SPRING_PROFILES_ACTIVE=dev" -p 8080:8080 ghcr.io/hirakida/hello-spring-boot:0.0.1
```
