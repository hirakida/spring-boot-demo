mkdir -p build/dependency
(cd build/dependency; jar -xf ../libs/*.jar)
docker build -t hirakida/spring-boot-docker-demo .
