# data-rest sample

http://docs.spring.io/spring-data/rest/docs/current/reference/html/
https://github.com/spring-projects/spring-boot/tree/master/spring-boot-samples/spring-boot-sample-data-rest


```
# findAll
http://localhost:8080/api/accounts
http://localhost:8080/api/accounts?page={page}&size={size}

# descending order
http://localhost:8080/api/accounts?sort=id,desc

# findById
http://localhost:8080/api/accounts/{id}

# findByName
http://localhost:8080/api/accounts/search/findByName?name={name}
```
