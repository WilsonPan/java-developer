FROM eclipse-temurin:21-jre-alpine AS runtime

# 创建应用目录
WORKDIR /app

# 复制jar文件
COPY target/spring-k8s-demo-0.0.1-SNAPSHOT.jar app.jar

# 暴露端口
EXPOSE 8080

# 启动应用
ENTRYPOINT ["java", "-jar", "app.jar"]