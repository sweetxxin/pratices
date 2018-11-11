package com.xxin.demo.rabbitMq.task;

import com.alibaba.fastjson.JSON;
import com.xxin.demo.rabbitMq.constants.Constants;
import com.xxin.demo.rabbitMq.entity.BrokerMessageLog;
import com.xxin.demo.rabbitMq.entity.Orders;
import com.xxin.demo.rabbitMq.repository.MessageLogRepository;
import com.xxin.demo.rabbitMq.service.MessageLogService;
import com.xxin.demo.rabbitMq.service.OrderSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Component
public class RetryMessageTask {
    @Autowired
    private OrderSender orderSender;
    @Autowired
    private MessageLogService logService;

    @Scheduled(initialDelay = 3000,fixedDelay  = 1000000)
    public void reSend(){
        System.err.println("--------------定时任务开始-------------------------");
        List<BrokerMessageLog> logs = logService.query4StatusAndTimeoutMessage();
        logs.forEach(messageLog ->{
            if (messageLog.getTryCount() >= 3){
                logService.changeBrokerMessageLogStatus(Constants.ORDER_SENDING_FAILURE, new Timestamp(new Date().getTime()), messageLog.getMessageId());
            }else{
                logService.update4Resend(new Timestamp(new Date().getTime()), messageLog.getMessageId());
                 Orders order = JSON.parseObject(messageLog.getMessage(), Orders.class);
                 orderSender.sendOrder(order);
            }
        });
    }
}
