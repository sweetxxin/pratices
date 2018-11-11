package com.xxin.demo;


import com.xxin.demo.activeMq.springJms.Producer;
import com.xxin.demo.activeMq.springJms.ProducerService;
import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActiveMqTest {
@Autowired
    ProducerService service;
    @Autowired
    private Producer producer;

@Test
    public void test() {
    for (int i = 0; i < 100; i++) {
        service.sendMessage("消息" + i);
    }
}
    @Test
    public void contextLoads(){
        ActiveMQQueue destination = new ActiveMQQueue("mytest.queue");
        for(int i=0; i<100; i++){
            producer.sendMessage(destination, "myname is chhliu!!!");
        }
    }
}
