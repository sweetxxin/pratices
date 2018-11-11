package com.xxin.demo.rabbitMq.service;

import com.xxin.demo.rabbitMq.constants.Constants;
import com.xxin.demo.rabbitMq.entity.BrokerMessageLog;
import com.xxin.demo.rabbitMq.entity.Orders;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;


@Service
public class OrderSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private MessageLogService messageLogService;

    final RabbitTemplate.ConfirmCallback callback = new RabbitTemplate.ConfirmCallback(){
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String s) {
            System.err.println("correlationData: "+correlationData);
            String messageId = correlationData.getId();
            if (ack){
                messageLogService.changeBrokerMessageLogStatus(Constants.ORDER_SENDING_SUCCESS,new Timestamp(new Date().getTime()),messageId);
                System.out.println("发送成功");
            }else{
                System.err.println("异常处理");
            }
        }
    };


    public void sendOrder(Orders order){
        rabbitTemplate.setConfirmCallback(callback);
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(order.getMessageId());
        rabbitTemplate.convertAndSend(
                "order-exchange-000",//交换机
                "order.abcd",//路由键值
                order,//消息体
                correlationData //消息唯一id
        );
    }
}
