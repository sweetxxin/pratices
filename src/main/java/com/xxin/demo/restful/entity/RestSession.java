package com.xxin.demo.restful.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "rest_session", schema = "java", catalog = "")
public class RestSession implements Serializable {
    private int sessionId;
    private Integer appId;
    private String accessToken;
    private Timestamp createTime;
    private Timestamp endTime;
    private int status;

    @Id
    @Column(name = "sessionId", nullable = false)
    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    @Basic
    @Column(name = "appId", nullable = true)
    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    @Basic
    @Column(name = "access_token", nullable = true, length = 255)
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
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
    @Column(name = "end_time", nullable = true)
    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    @Basic
    @Column(name = "status", nullable = false)
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestSession session = (RestSession) o;
        return sessionId == session.sessionId &&
                status == session.status &&
                Objects.equals(appId, session.appId) &&
                Objects.equals(accessToken, session.accessToken) &&
                Objects.equals(createTime, session.createTime) &&
                Objects.equals(endTime, session.endTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(sessionId, appId, accessToken, createTime, endTime, status);
    }

    @Override
    public String toString() {
        return "RestSession{" +
                "sessionId=" + sessionId +
                ", appId=" + appId +
                ", accessToken='" + accessToken + '\'' +
                ", createTime=" + createTime +
                ", endTime=" + endTime +
                ", status=" + status +
                '}';
    }
}
