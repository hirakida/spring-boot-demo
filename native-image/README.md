
https://docs.spring.io/spring-boot/docs/3.2.x/reference/html/native-image.html

Buildpack
```
% ../gradlew bootBuildImage
% docker run --rm -p 8080:8080 docker.io/library/native-image:0.0.1-SNAPSHOT
```

Native Build Tool
```
% sdk install java 23.1.2.r21-nik
% sdk use java 23.1.2.r21-nikqq

% ../gradlew nativeCompile
% ./build/native/nativeCompile/native-image
```
