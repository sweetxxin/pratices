package com.xxin.demo.springData.dao;

import com.xxin.demo.springData.domain.Student;

import java.util.List;

public interface StudentDao {
    public List<Student> queryAll();
    public void save(Student student);
}
