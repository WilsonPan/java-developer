package com.example.spring.k8s.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.k8s.demo.entity.User;
import com.example.spring.k8s.demo.mapper.UserMapper;

@RestController
@RequestMapping("user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/add")
    public String add() {
        var user = new User();
        user.setEmail("wilsonpan@163.com");
        user.setName("WilsonPan");

        userMapper.insert(user);
        logger.info("User Added: " + user.getId());
        return "User Added: " + user.getId();
    }

    @GetMapping("/get/{id}")
    public String get(@PathVariable Long id) {
        var user = userMapper.findById(Long.valueOf(id));
        logger.info("User: " + user.getName());
        return "User: " + user;
    }
}
