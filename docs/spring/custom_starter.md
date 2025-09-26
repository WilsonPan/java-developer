# Spring Boot 自定义Starter

## 为什么要自定义Starter

Spring Boot Starter 是一种简化依赖管理和自动配置的机制。下面详细介绍如何创建自定义 Starter。

## Starter 的基本概念

### 命名规范

官方   Starter: spring-boot-starter-{name}
自定义 Starter: {name}-spring-boot-starter

### 核心组件

```autoconfigure``` 模块: 包含自动配置逻辑

```starter``` 模块: 只包含依赖管理


## 创建自定义 Starter

### 项目结构

```md
my-starter/
├── my-spring-boot-autoconfigure/
│   ├── src/main/java/
│   │   └── com/example/mystarter/
│   └── pom.xml
└── my-spring-boot-starter/
    ├── src/main/resources/
    │   └── META-INF/
    │       └── spring.factories
    └── pom.xml
```

### 自动配置模块

```xml
<!-- my-spring-boot-autoconfigure/pom.xml -->
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-autoconfigure</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-configuration-processor</artifactId>
        <optional>true</optional>
    </dependency>
</dependencies>
```

### 配置属性类

```java
@ConfigurationProperties(prefix = "my.service")
public class MyServiceProperties {
    private String prefix = "Hello";
    private String suffix = "!";
    
    // getters and setters
    public String getPrefix() { return prefix; }
    public void setPrefix(String prefix) { this.prefix = prefix; }
    
    public String getSuffix() { return suffix; }
    public void setSuffix(String suffix) { this.suffix = suffix; }
}
```

### 服务类

```java
public class MyService {
    private final String prefix;
    private final String suffix;
    
    public MyService(String prefix, String suffix) {
        this.prefix = prefix;
        this.suffix = suffix;
    }
    
    public String wrap(String message) {
        return prefix + " " + message + " " + suffix;
    }
}
```

### 自动配置类

```java
@Configuration
@ConditionalOnClass(MyService.class)
@EnableConfigurationProperties(MyServiceProperties.class)
public class MyServiceAutoConfiguration {
    
    @Bean
    @ConditionalOnMissingBean
    public MyService myService(MyServiceProperties properties) {
        return new MyService(properties.getPrefix(), properties.getSuffix());
    }
    
    @Bean
    @ConditionalOnProperty(name = "my.service.enabled", havingValue = "true")
    @ConditionalOnMissingBean
    public MyServiceController myServiceController(MyService myService) {
        return new MyServiceController(myService);
    }
}
```

### 注册自动配置
在 src/main/resources/META-INF/spring.factories 中：

```
org.springframework.boot.autoconfigure.EnableAutoConfiguration=com.example.mystarter.MyServiceAutoConfiguration
```

需要注意，spring boot 3.x 取消了spring.factories, 替代方案为在路径 `src/main/resources/META-INF/spring` 创建一个文件`org.springframework.boot.autoconfigure.AutoConfiguration.imports`

把需要自动装配一行一行放入

```
com.example.mystarter.MyServiceAutoConfiguration
```