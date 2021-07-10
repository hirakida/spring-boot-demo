https://spring.io/guides/topicals/spring-boot-docker/  
https://spring.io/guides/gs/spring-boot-docker/  
https://docs.spring.io/spring-boot/docs/2.5.x/gradle-plugin/reference/htmlsingle/#build-image  

```
$ echo $GHCR_TOKEN | docker login ghcr.io -u hirakida --password-stdin

$ ./gradlew buildimage:bootBuildImage   

$ docker pull ghcr.io/hirakida/hello-docker:0.0.1

$ docker run -e "SPRING_PROFILES_ACTIVE=dev" -p 8080:8080 ghcr.io/hirakida/hello-docker:0.0.1
```
