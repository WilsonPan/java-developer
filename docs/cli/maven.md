# Maven

- [Maven](#maven)
  - [mvn archetype](#mvn-archetype)
  - [Maven 常用参数](#maven-常用参数)
  - [常用maven命令](#常用maven命令)
  - [web项目相关命令](#web项目相关命令)

## mvn archetype

```sh
# 创建原型

# 1. 在当前目录生成archetype
mvn archetype:create-from-project -DpackageName=com.wilson.demo
# 2. 进去生成archetype并install到本地仓库(有需要可发布私服，供其他人使用)
cd target/generated-sources/archetype/ && mvn install

# 根据自定义原型创建项目
mvn archetype:generate \
-DgroupId=com.wilson \ 
-DartifactId=webapps-demo \
-DarchetypeGroupId=com.wilson \
-DarchetypeArtifactId=webapps-archetype 

# 创建普通Java项目
mvn archetype:generate -DgroupId=com.wilson -DartifactId=app

# 创建Maven的Web项目
mvn archetype:generate -DgroupId=com.wilson -DartifactId=webapps -DarchetypeArtifactId=maven-archetype-webapp
```

## Maven 常用参数

1. -D: 指定参数，如 -Dmaven.test.skip=true 跳过单元测试
2. -P 指定 Profile 配置，可以用于区分环境
4. -e 显示maven运行出错的信息
5. -o 离线执行命令,即不去远程仓库更新包
6. -X 显示maven允许的debug信息
7. -U 强制去远程更新snapshot的插件或依赖，默认每天只更新一次


## 常用maven命令

```sh
# 1. 清理
mvn clean                                       # 清理maven项目
mvn eclipse:clean                               # 清理eclipse配置
mvn idea:clean                                  # 清理idea配置

# 2. 编译
mvn compile                                     # 编译源代码
mvn test-compile                                # 编译测试代码

# 3. 测试
mvn test                                        # 运行测试
mvn integration-test                            # 在集成测试可以运行的环境中处理和发布包

# 4. 安装发布
mvn package                                     # maven 打包
mvn install                                     # 安装项目到本地仓库
mvn deploy                                      # 发布项目到远程仓库

# 5. 其他
mvn validate                                    # 验证项目是否正确
mvn dependency:tree                             # 显示maven依赖树
mvn dependency:list                             # 显示maven依赖列表
```

## web项目相关命令

```sh
mvn tomcat:run                                  # 启动tomcat
mvn jetty:run                                   # 启动jetty
mvn tomcat:deploy                               # 运行打包部署
mvn tomcat:undeploy                             # 撤销部署
mvn tomcat:start                                # 启动web应用
mvn tomcat:stop                                 # 停止web应用
mvn tomcat:redeploy                             # 重新部署
mvn war:exploded tomcat:exploded                # 部署展开的war文件
```