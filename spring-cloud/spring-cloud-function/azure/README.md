
local
```
% mvn azure-functions:run

% curl -H "Content-Type: text/plain" http://localhost:8080/uppercase -d hello
```

Azure
```
% az login
% mvn azure-functions:deploy

% curl -H "Content-Type: text/plain" https://<application name>.azurewebsites.net/api/uppercase -d hello
```
