# spring-cloud-zookeeper-demo

start zookeeper
```
$ docker-compose up -d
```

start app
```
$ java -jar target/zookeeper-0.0.1-SNAPSHOT.jar --server.port=8080
$ java -jar target/zookeeper-0.0.1-SNAPSHOT.jar --server.port=8081
```

https://github.com/spring-cloud/spring-cloud-zookeeper  
http://cloud.spring.io/spring-cloud-zookeeper/spring-cloud-zookeeper.html  
http://curator.apache.org/curator-recipes/leader-latch.html  
