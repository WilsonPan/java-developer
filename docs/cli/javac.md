# javac

1. 编译

```sh
javac  App.java                                 # 编译Java文件
javac -g App.java                               # 生成所有调试信息
javac -g:none App.java                          # 不生成任何调试信息
javac -g:{lines,source} App.java                # 只生成某些调试信息
javac -d ./target                               # 指定放置生成的类文件的位置
javac -sourcepath ./src  ./src/App.java         # 指定查找输入源文件的位置
javac -verbose -sourcepath ./src ./src/App.java # 输出有关编译器正在执行的操作的消息
```

2. 执行

```sh
java App                            # 执行
```

## 引用

[https://github.com/WilsonPan/java-developer](https://github.com/WilsonPan/java-developer)