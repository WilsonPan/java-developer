# Redis

## 连接

```sh
redis-cli                                           # 连接本地服务
redis-cli -h {ip/host} -p 6379                      # 链接远程无密码服务
redis-cli -h {ip/host} -p 6379  -a {password}       # 链接远程有密码服务
redis-cli -u redis://username:password@host:port    # 链接账号密码服务     
```