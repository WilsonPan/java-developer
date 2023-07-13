package com.example.jpademo;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.jpademo.entity.Order;
import com.example.jpademo.reposity.OrderReposity;


@SpringBootTest
@RunWith(SpringRunner.class)
public class JpaTest {

    @Autowired
    private OrderReposity orderReposity;

    @Test
    void testJPA() {
        Order order = new Order();
        order.setId("123");
        order.setAmount(new BigDecimal(100));
        order.setCreateTime(new Date());
        order.setRequestId("123");
        order.setStatus("INIT");

        order = orderReposity.saveAndFlush(order);
    }

    @Test
    void testQuery() {
        Order order= orderReposity.findByRequestId("123");

        System.out.println(order.toString());
    }
}
