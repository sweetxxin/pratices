package com.xxin.demo.activeMq.jmsAPI.queue;


import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class AppProducer {
    private static final String url = "tcp://127.0.0.1:61616";
    public static final String queueName = "queue-test";
    public static void main(String[] args) throws JMSException {
        //1.创建ConnectionFactory
        ConnectionFactory factory = new ActiveMQConnectionFactory(url);
        //2.创建 Connection
        Connection connection = factory.createConnection();
        //3.启动连接
        connection.start();
      //4.创建会话
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
      //5.创建目标
        Destination destination = session.createQueue(queueName);
        //6.创建生产者
        MessageProducer producer = session.createProducer(destination);
        for (int i=0;i<100;i++){
            //7.创建消息
            TextMessage textMessage = session.createTextMessage("message:"+i);
            producer.send(textMessage);
            System.out.println("发送消息"+textMessage);
        }
        connection.close();
    }
}
