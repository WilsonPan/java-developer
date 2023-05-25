package com.samples.consumer.test;

import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.dubbo.samples.service.DemoService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DemoTest {

    @DubboReference
    private DemoService demoService;

    @Test
    public void testDemo() {
        String result = demoService.getName();
        Assert.assertEquals("Wilson Pan", result);
    }
}
