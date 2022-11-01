# Gradle

## 1. 安装 Gradle

```sh
brew install gradle     # 安装gradle

gradle -v               # gradle -v
```

## 初始化项目

```sh
# 1. 创建目录 
mkdir demo && cd demo

# 2. 初始化 gradle 项目
gradle init
```

## gradle 命令

```sh
gradle clean                # 删除 build 生成的目录和所有生成的文件.
gradle assemble             # 编译并打包你的代码, 但是并不运行单元测试
gradle build                # 构建项目
gradle bootJar              # 构建可执行的 jar
gradle jar                  # 编译打包主要的类文件
gradle classes              # 编译打包主要的类文件
gradle test                 # 执行单元测试
gradle check                # 执行验证任务的聚合任务，例如运行测试
gradle run                  # 运行程序
```

> gradle build 命令的输出

```sh
:compileJava
:processResources
:classes
:jar
:assemble
:compileTestJava
:processTestResources
:testClasses
:test
:check
:build
```

## VSCode 集成Gradle

1. 安装插件[Gradle for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-gradle)
2. 安装插件[Spring Initializr Java Support](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-spring-initializr)
3. command + shift + p 选择 `Spring Initializr: Create a Gradle Project`
4. 按提示选择

## 引用

[gradle](https://docs.gradle.org/current/samples/sample_building_java_applications.html#review_the_project_files)