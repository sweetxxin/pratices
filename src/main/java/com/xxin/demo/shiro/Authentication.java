package com.xxin.demo.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;

public class Authentication {
    public static SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

    public static void main(String[] args) {
        simpleAccountRealm.addAccount("xxin", "123","admin");
        //1.构建SecurityManager环境
        DefaultSecurityManager securityManager = new DefaultSecurityManager();

        //2.主体提交认证请求
        SecurityUtils.setSecurityManager(securityManager);
        securityManager.setRealm(simpleAccountRealm);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("xxin","123");
        subject.login(token);
        System.out.println("是否已经认证:"+subject.isAuthenticated());
        subject.checkRole("admin");
        subject.logout();
    }
}
