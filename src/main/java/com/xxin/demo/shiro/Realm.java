package com.xxin.demo.shiro;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

public class Realm {
    public static void main(String[] args) {
        //iniRealm();
        jdbcRealm();
        Md5Hash md5Hash = new Md5Hash("123");
        System.out.println(md5Hash);

    }
    public static void iniRealm(){
        IniRealm iniRealm = new IniRealm("classpath:user.ini");
        //1.构建SecurityManager环境
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(iniRealm);
        //2.主体提交认证请求
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("Xxin","1234");
        subject.login(token);
        System.out.println("是否已经认证:"+subject.isAuthenticated());
        subject.checkRole("admin");
        subject.checkPermission("user:delete");
        subject.logout();
    }
    public static void jdbcRealm(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/test?useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        JdbcRealm jdbcRealm = new JdbcRealm();
        jdbcRealm.setDataSource(dataSource);
        jdbcRealm.setPermissionsLookupEnabled(true);

        String sql = "select pass from test where name = ?";
        jdbcRealm.setAuthenticationQuery(sql);

        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
        matcher.setHashIterations(1);
        jdbcRealm.setCredentialsMatcher(matcher);

//        Factory<SecurityManager> factory =
//                new IniSecurityManagerFactory("classpath:shiro-conf.ini");
  //      SecurityManager securityManager = factory.getInstance();

        //1.构建SecurityManager环境
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(jdbcRealm);
        //2.主体提交认证请求
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("xxin","123");
        subject.login(token);
        System.out.println("是否已经认证:"+subject.isAuthenticated());
//        subject.checkRole("admin");
//        subject.checkPermission("user:select");
        subject.logout();
    }
}
