package com.example.spring.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.mystarter.MyService;
import com.example.mystarter.MyServiceProperties;

@SpringBootTest
@EnableConfigurationProperties(MyServiceProperties.class)
class MyServiceIntegrationTest {
    
    @Autowired
    private MyService myService;
    
    @Test
    void testServiceFunctionality() {
        String result = myService.wrap("World");
        
        assertNotNull(result);
        assertEquals(result, "Hello World !");
    }
}
