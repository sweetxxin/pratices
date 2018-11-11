package com.xxin.demo.springData.domain;

import org.springframework.context.annotation.Primary;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Student {
    @Id
    private int sid;
    private String name;
    private String className;
    private String gender;
    public Student(){

    }

    public Student(int sid, String name, String className, String gender) {
        this.sid = sid;
        this.name = name;
        this.className = className;
        this.gender = gender;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int id) {
        this.sid = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sid=" + sid +
                ", name='" + name + '\'' +
                ", className='" + className + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
