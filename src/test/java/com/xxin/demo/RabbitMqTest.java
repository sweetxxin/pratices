package com.xxin.demo;


import com.xxin.demo.rabbitMq.entity.Orders;
import com.xxin.demo.rabbitMq.service.OrderSender;
import com.xxin.demo.rabbitMq.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMqTest {
@Autowired
    private OrderSender orderSender;
@Autowired
    private OrderService orderService;
@Test
    public void testSender(){
    orderSender.sendOrder(new Orders("2018102100001","测试订单1",System.currentTimeMillis()+"$"+UUID.randomUUID()));
}
@Test
    public void createOrder(){
    Orders order = new Orders();
    order.setId("201810220000005");
    order.setMessageId(System.currentTimeMillis()+"$"+UUID.randomUUID());
    order.setName("创建测试订单");
    orderService.createOrder(order);
}
}
