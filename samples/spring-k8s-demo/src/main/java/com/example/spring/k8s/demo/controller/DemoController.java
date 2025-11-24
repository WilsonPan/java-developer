package com.example.spring.k8s.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    
    private static final Logger logger = LoggerFactory.getLogger(DemoController.class);

    @GetMapping("/")
	public String hello() {
		// 测试日志
		logger.info("Hello from Spring Boot on Kubernetes!");
        return "Hello from Spring Boot on Kubernetes!";
	}

    @GetMapping("/health")
	public String health() {
		return "OK";
	}
}
