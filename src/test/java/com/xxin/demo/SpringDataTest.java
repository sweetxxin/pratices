package com.xxin.demo;

import com.xxin.demo.springData.dao.StudentDao;
import com.xxin.demo.springData.domain.Employ;
import com.xxin.demo.springData.domain.Student;
import com.xxin.demo.springData.impl.StudentDaoImpl;
import com.xxin.demo.springData.impl.StudentDaoJdbcTemplateImpl;
import com.xxin.demo.springData.repository.EmployCrudRepository;
import com.xxin.demo.springData.repository.EmployRepository;
import com.xxin.demo.springData.service.EmployService;
import com.xxin.demo.springData.utils.JDBCUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import javax.swing.plaf.PanelUI;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDataTest {
    @Autowired
    @Qualifier("studentDaoJdbcTemplateImpl")
    StudentDao studentDao;

    @Autowired
    DataSource dataSource;

    @Autowired
    EmployRepository employRepository;

    @Autowired
    EmployService employService;

    @Test
    public void testConnection() throws SQLException, ClassNotFoundException, IOException {
        Connection connection = JDBCUtils.getConnection();
        Assert.assertNotNull(connection);
    }
    @Test
    public void testQueryAll(){
        StudentDao dao = new StudentDaoImpl();
        System.out.println(dao.queryAll());
    }
    @Test
    public void testSave(){
        StudentDao dao = new StudentDaoImpl();
        dao.save(new Student(54,"林允儿","外包161","女"));
    }
    @Test
    public void testDataSource(){
        System.out.println("-----------------"+dataSource);
    }
    @Test
    public void testJdbcTemplate(){
        System.out.println(studentDao.queryAll());
        studentDao.save(new Student(44,"周杰伦","外包161","男"));
    }
    @Test
    public void testRepository(){
//        System.out.println("****"+employRepository.findByName("林允儿")+"****");
  //      System.out.println(employRepository.findByNameEndingWithAndAgeIsLessThan("允儿",30 ));
    //    System.out.println(employRepository.findMaxId());
      //  System.out.println(employRepository.findByParams1("林允儿",28 ));
       //  System.out.println(employRepository.findByParams2("林允儿",28 ));
       // System.out.println(employRepository.findNameLike("允儿"));
//        System.out.println(employRepository.count());
      //  employRepository.updateAgeById(2,33 );
        employService.updateAgeById(2,23 );
    }
    @Test
    public void testCrudRepository(){
        List<Employ>employs = new ArrayList<>();
        for (int i=5;i<100;i++){
            employs.add(new Employ(i,"test"+i,i+10));
        }
        employService.save(employs);
    }
    @Test
    public void testPage(){
        employService.page(0,5 );
    }
    @Test
    public void testPageAndSort(){
        employService.page(0,5 );
    }
    @Test
    public void testJpaFindById(){
        System.out.println(employService.findOne(2));
    }
    @Test
    public void testJpaExists(){
        System.out.println(employService.exists(2));
        System.out.println(employService.exists(200));
    }
    @Test
    public void testQueryGtAge(){
        employService.queryGtAge(0, 5, 20);
    }
}
