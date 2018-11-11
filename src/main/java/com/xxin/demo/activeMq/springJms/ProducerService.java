package com.xxin.demo.activeMq.springJms;

import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;

@Service
public class ProducerService {
    @Autowired
    JmsTemplate template;
    @Resource(name = "queue")
    Destination destination;
    public  void sendMessage(final String message){
        template.send(destination, new MessageCreator() {
            @Override
            public javax.jms.Message createMessage(Session session) throws JMSException {
                TextMessage textMessage =  session.createTextMessage(message);
                System.out.println("****发送消息："+textMessage.getText()+"***********");
                return textMessage;
            }
        });
    }
}
