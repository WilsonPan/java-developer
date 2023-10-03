# Linux 常用命令

## 查找进程

```sh
# 根据关键字查找
ps -ef | grep -v grep | grep java

# kill 相关进程
ps -ef | grep -v grep | grep java | awk '{print $2}' | xargs kill -9
```