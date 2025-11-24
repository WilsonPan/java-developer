package com.example.spring.k8s.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.spring.k8s.demo.entity.User;

@Mapper
public interface UserMapper {
    User findById(Long id);
    List<User> findAll();
    int insert(User user);
    void update(User user);
    void delete(Long id);
}
