package com.xxin.demo.activeMq.springJms.conf;


import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQDestination;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Queue;
//    @Configuration
//    @EnableJms
    public class ActiveMqConf {

        @Value("${spring.activemq.broker-url}")
        private String broker_url;

        @Value("${spring.activemq.user}")
        private String jmsUser;

        @Value("${spring.activemq.password}")
        private String jsmPass;

        private static final String QUEUE_NAME_ = "spring-mq-queue";

        private static final String TOPIC_NAME = "spring-mq-orders";
        @Bean("queue")
        public Queue queueQueue(){
            return new ActiveMQQueue(QUEUE_NAME_);
        }

        @Bean("topicQueue")
        public ActiveMQTopic topicQueue(){
            return new ActiveMQTopic(TOPIC_NAME);
        }

        @Bean(name = "activeMQConnectionFactory")
        public ActiveMQConnectionFactory activeMQConnectionFactory(){
            ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(jmsUser,jsmPass,broker_url);
            activeMQConnectionFactory.setTrustAllPackages(true);

            return activeMQConnectionFactory;
        }

        @Bean(name = "queueListenerFactory")
        public JmsListenerContainerFactory<?> queueListenerFactory(ConnectionFactory activeMQConnectionFactory){
            DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
            factory.setPubSubDomain(false);
            factory.setConnectionFactory(activeMQConnectionFactory);
            return factory;
        }

        @Bean(name = "topicListenerFactory")
        public JmsListenerContainerFactory<DefaultMessageListenerContainer> topicListenerFactory(ConnectionFactory activeMQConnectionFactory){
            DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();

            factory.setConnectionFactory(activeMQConnectionFactory);
            return factory;

        }
        @Bean
        public JmsMessagingTemplate jmsMessagingTemplate(ConnectionFactory activeMQConnectionFactory){
            return new JmsMessagingTemplate(activeMQConnectionFactory);
        }
    }

