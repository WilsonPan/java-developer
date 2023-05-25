package com.example.dubbo.samples.provider;

import org.apache.dubbo.config.annotation.DubboService;

import com.example.dubbo.samples.service.DemoService;

@DubboService
public class DemoServiceImpl implements DemoService {

    @Override
    public String getName() {
        return "Wilson Pan";
    }

}
