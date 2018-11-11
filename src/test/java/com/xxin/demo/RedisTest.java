package com.xxin.demo;

import com.xxin.demo.restful.entity.RestSession;
import com.xxin.demo.restful.utils.RedisUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate template;
    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void save(){
        stringRedisTemplate.opsForValue().set("zzp","big z");
        Assert.assertEquals("big z",stringRedisTemplate.opsForValue().get("zzp"));
    }
    @Test
    public void testobjSerializer() throws InterruptedException {
        RestSession session = new RestSession();
        session.setSessionId(123);
        session.setAccessToken("as;fhaskf;hasfas");
        session.setAppId(93741029);
        session.setStatus(1);
        ValueOperations operations = template.opsForValue();
        operations.set("test.s1",session);
        operations.set("test.s2",session,1,TimeUnit.SECONDS);
        Thread.sleep(1000);
        Thread.sleep(1000);
        boolean exists=template.hasKey("test.s1");
        System.out.println("*********************"+exists+"**************");
        System.out.println(template.opsForValue().get("test.s1"));
        RestSession session1 =(RestSession) redisUtil.get("test.s1");
        System.out.println(session1.getAccessToken());
    }
}
