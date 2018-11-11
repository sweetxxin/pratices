package com.xxin.demo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PraticeApplicationTests {
    SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();
    @Before
    public void addUser(){
        simpleAccountRealm.addAccount("xxin", "1234");
    }
    @Test
    public void testAuthentication(){

        //1.构建SecurityManager环境
        DefaultSecurityManager securityManager = new DefaultSecurityManager();

        //2.主体提交认证请求
        SecurityUtils.setSecurityManager(securityManager);
        securityManager.setRealm(simpleAccountRealm);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("xxin","12345");
        subject.login(token);
        System.out.println("是否已经认证:"+subject.isAuthenticated());
        subject.logout();
    }
}
