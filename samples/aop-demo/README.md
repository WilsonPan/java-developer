# AOP 

1. 引入

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-aop</artifactId>
</dependency>
```

2. 定义接口

```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CacheResult {
}
```

3. 定义Aspect类

```java
@Aspect
@Component
public class CacheResultAspect {
    private static ConcurrentHashMap<String, Object> cache = new ConcurrentHashMap<>();

    @Around(value = "@annotation(cacheResult)")
    private Object around(ProceedingJoinPoint joinPoint, CacheResult cacheResult) throws Throwable {
        Object[] args = joinPoint.getArgs();
        
        String key = buildKey(args);

        if(cache.containsKey(key)){
            return cache.get(key);
        }
        Object result =  joinPoint.proceed();

        cache.put(key, result);

        return result;
    }

    private String buildKey(Object[] args){
        return String.format("cache_%s", args[0].toString());
    } 

}
```