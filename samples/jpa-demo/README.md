# Jpa

## 安装

> 使用Docker

```sh
# 1. 安装最新版本
docker pull mysql

# 2. 启动容器
docker run --name mysql \
--restart always    \
-p 3306:3306    \
-e MYSQL_ROOT_PASSWORD=root    \
-v $PWD/mysql/data:/var/lib/mysql   \
-v $PWD/mysql/conf.d:/etc/mysql/conf.d   \
-v $PWD/mysql/logs:/var/log/mysql   \
-d mysql

## --name 指定运行容器名称
## --restart always
## -p 3306:3306: 映射主机 3306 端口到容器 3306 端口
## -e MYSQL_ROOT_PASSWORD=12345: 指定 msyql root 密码
## $PWD/mysql/data:/var/lib/mysql 数据持久化
## -d 后台运行

# 3. 进入容器mysql

docker exec -it mysql bash

# 4. 登录mysql
mysql -u root -p

# 5. 修改密码方式
use mysql;

alter user 'root'@'%' identified with mysql_native_password by 'root';

alter user 'root'@'localhost' identified with mysql_native_password by 'root';
```

