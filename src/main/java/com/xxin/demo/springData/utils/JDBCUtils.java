package com.xxin.demo.springData.utils;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

@Component
public class JDBCUtils {
    public static Connection getConnection() throws ClassNotFoundException, SQLException, IOException {
        InputStream stream = JDBCUtils.class.getClassLoader().getResourceAsStream("springData/db.properties");
        Properties property = new Properties();
        property.load(stream);
        String driveClass = property.getProperty("jdbc.driveClass");
        String url= property.getProperty("jdbc.url");
        String user= property.getProperty("jdbc.user");
        String password= property.getProperty("jdbc.password");
        Class.forName(driveClass);
        return DriverManager.getConnection(url,user,password);
    }
    public static void release(ResultSet resultSet, Statement statement,Connection connection) {

        if (resultSet!=null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
