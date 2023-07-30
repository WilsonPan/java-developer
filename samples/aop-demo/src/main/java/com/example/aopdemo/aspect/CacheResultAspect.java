package com.example.aopdemo.aspect;

import java.util.concurrent.ConcurrentHashMap;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

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
