package com.xxin.demo.rabbitMq.service;

import com.alibaba.fastjson.JSON;
import com.xxin.demo.rabbitMq.constants.Constants;
import com.xxin.demo.rabbitMq.entity.BrokerMessageLog;
import com.xxin.demo.rabbitMq.entity.Orders;
import com.xxin.demo.rabbitMq.repository.MessageLogRepository;
import com.xxin.demo.rabbitMq.repository.OrderRepository;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private MessageLogRepository messageLogRepository;
    @Autowired
    private OrderSender sender;

    @Transactional
    public void createOrder(Orders order){
        Date date = new Date();
        orderRepository.save(order);
        BrokerMessageLog log = new BrokerMessageLog();
        log.setMessageId(order.getMessageId());
        log.setMessage(JSON.toJSONString(order));
        log.setStatus("0");
        log.setTryCount(0);
        log.setNextRetry(new Timestamp(DateUtils.addMinutes(date,Constants.ORDER_TIMEOUT).getTime()));
        log.setCreateTime(new Timestamp(new Date().getTime()));
        log.setUpdateTime(new Timestamp(new Date().getTime()));
        messageLogRepository.save(log);
        sender.sendOrder(order);
    }
}
