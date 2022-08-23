
https://docs.spring.io/spring-native/docs/current/reference/htmlsingle/  

Buildpacks
```
% ./gradlew bootBuildImage
% docker run --rm -p 8080:8080 spring-native-demo:0.0.1-SNAPSHOT
```

Native Build
```
% sdk install java 22.2.r11-grl
% sdk use java 22.2.r11-grl
% gu install native-image

% ./gradlew nativeCompile
% build/native/nativeCompile/spring-native-demo
```
