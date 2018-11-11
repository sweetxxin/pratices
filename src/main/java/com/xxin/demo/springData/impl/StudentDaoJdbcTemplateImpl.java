package com.xxin.demo.springData.impl;

import com.xxin.demo.springData.dao.StudentDao;
import com.xxin.demo.springData.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentDaoJdbcTemplateImpl implements StudentDao {
    @Autowired
    JdbcTemplate template;

    @Override
    public List<Student> queryAll() {
      String sql = "select * from student";
      List<Student> students = new ArrayList<>();
      template.query(sql, new RowCallbackHandler() {
          @Override
          public void processRow(ResultSet resultSet) throws SQLException {
              int sid = resultSet.getInt("sid");
              String name = resultSet.getString("name");
              String gender = resultSet.getString("gender");
              String className = resultSet.getString("className");
              Student student = new Student();
              student.setSid(sid);
              student.setClassName(className);
              student.setGender(gender);
              student.setName(name);
              students.add(student);

          }
      });
      return students;
    }

    @Override
    public void save(Student student) {
        String sql = "insert into  student values (?,?,?,?)";
        template.update(sql,new Object[]{student.getSid(),student.getName(),student.getClassName(),student.getGender()});
    }
}
