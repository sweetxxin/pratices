package com.xxin.demo.rabbitMq.service;


import com.rabbitmq.client.Channel;
import com.xxin.demo.rabbitMq.entity.Orders;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class OrderReceiver {

 @RabbitListener(bindings = @QueueBinding(
         value = @Queue(value = "order-queue",durable = "true"),
         exchange = @Exchange(name = "order-exchange",durable = "true",type = "topic"),
         key = "order.*"
 ))
 @RabbitHandler
    public void onOrderMessage(@Payload Orders order, @Headers Map<String,Object> header, Channel channel) throws IOException {
     System.out.println("收到消息");
     System.out.println(order);
     Long deliveryTag = (Long) header.get(AmqpHeaders.DELIVERY_TAG);
     channel.basicAck(deliveryTag,false);
 }
}
