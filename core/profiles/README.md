application property
```
% java -jar build/libs/profiles-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev
```

system property
```
% java -Dspring.profiles.active=dev -jar build/libs/profiles-0.0.1-SNAPSHOT.jar 
```

environment variable
```
% export SPRING_PROFILES_ACTIVE=dev
% java -jar build/libs/profiles-0.0.1-SNAPSHOT.jar
```
