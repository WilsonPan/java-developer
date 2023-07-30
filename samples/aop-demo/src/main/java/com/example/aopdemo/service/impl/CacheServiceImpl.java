package com.example.aopdemo.service.impl;

import org.springframework.stereotype.Service;

import com.example.aopdemo.aspect.CacheResult;
import com.example.aopdemo.service.CacheService;

@Service
public class CacheServiceImpl implements CacheService {

    @Override
    @CacheResult
    public String getById(String id) {
        return "Wilson Pan" + id;
    }

}
