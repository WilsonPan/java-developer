# JPA Demo

## 安装mysql

```sh

## 1. 安装最新mysql
docker pull mysql:8.0.43 

## 2. 启动mysql
docker run --restart=always -d \
--name mysql -p 3306:3306  \
-v $PWD/mysql/conf.d:/etc/mysql/conf.d    \
-v $PWD/mysql/logs:/var/log/mysql   \
-v $PWD/mysql/data:/var/lib/mysql   \
-e MYSQL_ROOT_PASSWORD=root   \
mysql:8.0.43

## 3. 进入docker容器
docker exec -it mysql bash

## 4. 登录mysql
mysql -uroot -p

## 5. 外部登录
ALTER USER 'root'@'%' IDENTIFIED WITH mysql_native_password BY '123456';

flush privileges;
```

## jpa 