
https://docs.spring.io/spring-boot/docs/2.3.x/reference/html/howto.html#howto-set-active-spring-profiles  
https://docs.spring.io/spring-boot/docs/2.3.x/reference/html/spring-boot-features.html#boot-features-profiles  

application property
```
% java -jar build/libs/profiles-demo-0.1-SNAPSHOT.jar --spring.profiles.active=dev
```

system property
```
% java -Dspring.profiles.active=dev -jar build/libs/profiles-demo-0.1-SNAPSHOT.jar 
```

environment variable
```
% export SPRING_PROFILES_ACTIVE=dev
% java -jar build/libs/profiles-demo-0.1-SNAPSHOT.jar
```
