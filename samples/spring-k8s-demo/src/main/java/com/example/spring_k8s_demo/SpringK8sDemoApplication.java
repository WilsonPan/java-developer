package com.example.spring_k8s_demo;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringK8sDemoApplication {

	private static Date firstTime;
	private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static void main(String[] args) {
		SpringApplication.run(SpringK8sDemoApplication.class, args);
	}

	@GetMapping("/")
	public String hello() {
		if (firstTime == null) {
			firstTime = new Date();
		}
		// 容器里看日志是否有负载均衡
		System.out.println("request in " + formatter.format(new Date()));
		return "Hello from Spring Boot on Kubernetes! first time: " + formatter.format(firstTime);
	}

	@GetMapping("/health")
	public String health() {
		return "OK";
	}
}
