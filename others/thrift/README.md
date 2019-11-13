
```
$ brew install thrift
```

```
$ keytool -genkeypair -alias thrift -keyalg RSA -validity 365 -keystore keystore.jks
$ keytool -export -alias thrift -keystore keystore.jks -rfc -file cert.cer
$ keytool -import -alias thrift -file cert.cer -keystore truststore.jks

$ mv keystore.jks thrift-server/src/main/resources/
$ mv truststore.jks thrift-client/src/main/resources/
```
