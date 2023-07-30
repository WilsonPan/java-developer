package com.example.aopdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.aopdemo.service.CacheService;

@RestController
@RequestMapping(value = "/home")
public class HomeController {

    @Autowired
    private CacheService cacheService;

    @RequestMapping(value = "index")
    public String say(@RequestParam("id")String id){
        return cacheService.getById(id);
    }
}
