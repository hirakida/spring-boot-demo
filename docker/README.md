
https://docs.spring.io/spring-boot/docs/2.7.x/gradle-plugin/reference/htmlsingle/#build-image  

```
$ echo $GHCR_TOKEN | docker login ghcr.io -u hirakida --password-stdin

$ ./gradlew bootBuildImage   

$ docker pull ghcr.io/hirakida/hello-docker:0.0.1

$ docker run -e "SPRING_PROFILES_ACTIVE=dev" -p 8080:8080 ghcr.io/hirakida/hello-docker:0.0.1
```
