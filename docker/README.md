https://spring.io/guides/topicals/spring-boot-docker/  
https://spring.io/guides/gs/spring-boot-docker/  

Dockerfile
```
./gradlew build

docker run [-e "SPRING_PROFILES_ACTIVE=dev"] -p 8080:8080 hirakida/spring-boot-docker-demo
```

Jib
```
./gradlew jibDockerBuild 

docker run [-e "SPRING_PROFILES_ACTIVE=dev"] -p 8080:8080 hirakida/spring-boot-jib-demo
```
