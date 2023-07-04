package com.example.dubbo.samples.consumer;

import java.util.Date;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.dubbo.samples.service.DemoService;

@Component
public class Task implements CommandLineRunner {

    @DubboReference(timeout = 3000)
    private DemoService demoService;

    @Override
    public void run(String... args) throws Exception {
        String result = demoService.getName();
        System.out.println("Receive result ======> " + result);
    }
}
