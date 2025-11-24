package com.example.spring.k8s.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.spring.k8s.demo.mapper") 			// 扫描Mapper接口
public class SpringK8sDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringK8sDemoApplication.class, args);
	}
}
