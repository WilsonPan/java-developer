package com.example.jpademo.reposity;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jpademo.entity.Order;

public interface OrderReposity extends JpaRepository<Order, String> {
    
    public Order findByRequestId(String requestId);
}
