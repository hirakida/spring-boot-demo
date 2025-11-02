
Insert
```zsh
curl -X POST http://localhost:8080/users -H "Content-Type: application/json" -d '{"name":"name6","age":26}'
```

Update
```zsh
curl -X PUT http://localhost:8080/users/6 -H "Content-Type: application/json" -d '{"name":"name7","age":27}'
```

Page
http://localhost:8080/users?page=0&size=2
