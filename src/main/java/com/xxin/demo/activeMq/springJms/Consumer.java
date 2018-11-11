package com.xxin.demo.activeMq.springJms;


import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    @JmsListener(destination = "spring-mq-queue")
    public void receiveQueue(String text) {
        System.out.println("Consumer收到的报文为:"+text);
    }
}