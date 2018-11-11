package com.xxin.demo.rabbitMq.service;

import com.alibaba.druid.sql.PagerUtils;
import com.xxin.demo.rabbitMq.entity.BrokerMessageLog;
import com.xxin.demo.rabbitMq.repository.MessageLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
public class MessageLogService {
    @Autowired
    private MessageLogRepository messageLogRepository;

    @Transactional
    public void changeBrokerMessageLogStatus(String status, Timestamp updateTime,String messageId){
        messageLogRepository.changeBrokerMessageLogStatus(status,updateTime,messageId);
    }
    @Transactional
    public void update4Resend(Timestamp timestamp, String messageId){
        messageLogRepository.update4Resend(timestamp,messageId );
    }
    public List<BrokerMessageLog> query4StatusAndTimeoutMessage(){
        return messageLogRepository.query4StatusAndTimeoutMessage();
    }
}
