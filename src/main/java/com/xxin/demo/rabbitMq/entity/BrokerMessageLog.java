package com.xxin.demo.rabbitMq.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "broker_message_log", schema = "java", catalog = "")
public class BrokerMessageLog {
    @Id
    private String messageId;
    private String message;
    private Integer tryCount;
    private String status;
    private Timestamp nextRetry;
    private Timestamp createTime;
    private Timestamp updateTime;

    @Basic
    @Column(name = "message_id", nullable = true, length = 255)
    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    @Basic
    @Column(name = "message", nullable = true, length = 255)
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Basic
    @Column(name = "try_count", nullable = true)
    public Integer getTryCount() {
        return tryCount;
    }

    public void setTryCount(Integer tryCount) {
        this.tryCount = tryCount;
    }

    @Basic
    @Column(name = "status", nullable = true, length = 255)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "next_retry", nullable = true)
    public Timestamp getNextRetry() {
        return nextRetry;
    }

    public void setNextRetry(Timestamp nextRetry) {
        this.nextRetry = nextRetry;
    }

    @Basic
    @Column(name = "create_time", nullable = true)
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "update_time", nullable = true)
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BrokerMessageLog that = (BrokerMessageLog) o;
        return Objects.equals(messageId, that.messageId) &&
                Objects.equals(message, that.message) &&
                Objects.equals(tryCount, that.tryCount) &&
                Objects.equals(status, that.status) &&
                Objects.equals(nextRetry, that.nextRetry) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(messageId, message, tryCount, status, nextRetry, createTime, updateTime);
    }
}
