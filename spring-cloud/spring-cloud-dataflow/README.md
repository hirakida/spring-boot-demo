
```
% .gradlew clean build
% docker-compose up -d
% java -jar dataflow-shell/build/libs/dataflow-shell-0.0.1-SNAPSHOT.jar

dataflow:>app import --uri file:task-apps.properties
dataflow:>task create task1 --definition app1
dataflow:>task launch task1
```

http://localhost:9393/dashboard
