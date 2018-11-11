package com.xxin.demo.rabbitMq.repository;

import com.xxin.demo.rabbitMq.entity.BrokerMessageLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface MessageLogRepository extends JpaRepository<BrokerMessageLog,String> {

    @Modifying
    @Query("update BrokerMessageLog log " +
            "set log.status = :status," +
            "log.updateTime = :updateTime " +
            "where log.messageId = :messageId")
    public void changeBrokerMessageLogStatus(@Param("status") String status,
                                             @Param("updateTime")Timestamp updateTime,
                                             @Param("messageId") String messageId);

    @Query(value = "select * from broker_message_log WHERE status = '0' and next_retry <= sysdate()",nativeQuery = true)
    public List<BrokerMessageLog> query4StatusAndTimeoutMessage();

    @Modifying
    @Query("update BrokerMessageLog log set log.tryCount = log.tryCount+1,log.updateTime = :updateTime where log.messageId = :messageId")
    public void update4Resend(@Param("updateTime")Timestamp timestamp,@Param("messageId")String messageId);
}
