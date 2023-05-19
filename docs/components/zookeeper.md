# ZooKeeper使用

## 安装
> 使用Docker

```sh
# 1. 安装最新版本
docker pull zookeeper

# 2. 创建挂载目录（数据，配置，日志），只是测试可忽略这步

mkdir /Users/wilson/wilson.pan/volumes/zookeeper/data
mkdir /Users/wilson/wilson.pan/volumes/zookeeper/conf
mkdir /Users/wilson/wilson.pan/volumes/zookeeper/logs

# 3. 启动容器

cd /Users/wilson/wilson.pan/volumes/zookeeper

docker run -d --name zookeeper \
-v $PWD/data:/data  \
-v $PWD/conf:/conf  \
-v $PWD/logs:/logs  \
-p 2181:2181        \
-e TZ="Asia/Shanghai" \
zookeeper

# -d:       后台运行
# --name:   容器名称
# -v:       挂载目录
# -p:       端口映射
# -e TZ="Asia/Shanghai"

# 4. 查看容器

docker ps -a | grep zookeeper

# 5. 连接Zookeeper

docker run -it --rm --link zookeeper:zookeeper zookeeper zkCli.sh -server zookeeper ## 直接登录到容器时，进入到 zkCli

# 或者

docker exec -it zookeeper bash          ## 只登录容器，不登录zkCli

./bin/zkCli.sh                          ## 进入到 zkCli
```

