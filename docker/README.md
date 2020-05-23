https://spring.io/guides/topicals/spring-boot-docker/  
https://spring.io/guides/gs/spring-boot-docker/  
https://docs.spring.io/spring-boot/docs/current/gradle-plugin/reference/html/#build-image  

```
# BootBuildImage
./gradlew bootBuildImage

# Jib
./gradlew jibDockerBuild 

# DockerFile
./gradlew buildImage

docker run [-e "SPRING_PROFILES_ACTIVE=dev"] -p 8080:8080 hirakida/spring-boot-docker-demo
```
