package com.xxin.demo.rabbitMq.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "orders", schema = "java")
public class Orders implements Serializable {
    private String id;
    private String name;
    private String messageId;

    public Orders(){}
    public Orders(String id, String name, String messageId) {
        this.id = id;
        this.name = name;
        this.messageId = messageId;
    }
    @Id
    @Column(name = "id", nullable = false, length = 255)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "message_id", nullable = true, length = 255)
    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders orders = (Orders) o;
        return Objects.equals(id, orders.id) &&
                Objects.equals(name, orders.name) &&
                Objects.equals(messageId, orders.messageId);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, name, messageId);
    }
    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", messageId='" + messageId + '\'' +
                '}';
    }
}
