package com.xxin.demo.springData.impl;

import com.xxin.demo.springData.dao.StudentDao;
import com.xxin.demo.springData.domain.Student;
import com.xxin.demo.springData.utils.JDBCUtils;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentDaoImpl implements StudentDao {
    @Override
    public List<Student> queryAll() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet =null;
        String sql = "select * from student";
        List<Student> students = new ArrayList<>();
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            Student student = null;
            while (resultSet.next()){
                int sid = resultSet.getInt("sid");
                String name = resultSet.getString("name");
                String gender = resultSet.getString("gender");
                String className = resultSet.getString("className");
                student = new Student();
                student.setSid(sid);
                student.setClassName(className);
                student.setGender(gender);
                student.setName(name);
                students.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.release(resultSet, preparedStatement,connection );
        }
        return students;
    }

    @Override
    public void save(Student student) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "insert into  student values (?,?,?,?)";
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, student.getSid());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setString(3,student.getClassName());
            preparedStatement.setString(4,student.getGender());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.release(null, preparedStatement,connection );
        }
    }
}
