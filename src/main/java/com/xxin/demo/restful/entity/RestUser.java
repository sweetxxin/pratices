package com.xxin.demo.restful.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "rest_user", schema = "java", catalog = "")
public class RestUser {
    private int id;
    private int appId;
    private String appSecret;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "appId", nullable = false)
    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    @Basic
    @Column(name = "appSecret", nullable = false, length = 255)
    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestUser restUser = (RestUser) o;
        return id == restUser.id &&
                appId == restUser.appId &&
                Objects.equals(appSecret, restUser.appSecret);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, appId, appSecret);
    }
}
