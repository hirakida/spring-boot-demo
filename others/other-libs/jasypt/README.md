
```
% wget https://github.com/jasypt/jasypt/releases/download/jasypt-1.9.3/jasypt-1.9.3-dist.zip
% unzip jasypt-1.9.3-dist.zip
% chmod +x jasypt-1.9.3/bin/encrypt.sh
% jasypt-1.9.3/bin/encrypt.sh input=<message> password=<password> algorithm=PBEWITHHMACSHA512ANDAES_256 ivGeneratorClassName=org.jasypt.iv.RandomIvGenerator
```
