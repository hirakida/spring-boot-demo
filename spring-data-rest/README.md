# Spring Data REST

http://docs.spring.io/spring-data/rest/docs/current/reference/html/  
https://github.com/spring-projects/spring-boot/tree/master/spring-boot-samples/spring-boot-sample-data-rest  

```
# findAll
http://localhost:8080/api/users
http://localhost:8080/api/users?page={page}&size={size}

# descending order
http://localhost:8080/api/users?sort=id,desc

# findById
http://localhost:8080/api/users/{id}

# findByName
http://localhost:8080/api/users/search/findByName?name={name}
```
