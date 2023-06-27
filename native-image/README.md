
https://docs.spring.io/spring-boot/docs/3.1.x/reference/html/native-image.html

Buildpack
```
% ./gradlew bootBuildImage
% docker run --rm -p 8080:8080 docker.io/library/native-image-demo:0.0.1-SNAPSHOT
```

Native Build Tool
```
% sdk install java 22.3.2.r17-nik
% sdk use java 22.3.2.r17-nik

% ./gradlew nativeCompile
% % build/native/nativeCompile/native-image-demo
```
