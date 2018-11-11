package com.xxin.demo.activeMq.jmsAPI.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class AppConsumer {
    private static final String url = "tcp://127.0.0.1:61616";
    public static final String topicName = "topic-test";
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
        Destination destination = session.createTopic(topicName);
        //6.创建消费者
        MessageConsumer consumer = session.createConsumer(destination);
        //7.监听是否有信息
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage)message;
                try {
                    System.out.println("消费消息"+textMessage.getText());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}
