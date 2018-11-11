package com.xxin.demo.restful.service;

import com.sun.org.apache.regexp.internal.REUtil;
import com.xxin.demo.restful.entity.RestSession;
import com.xxin.demo.restful.repository.RestSessionRepository;
import com.xxin.demo.restful.utils.Encrypt;
import com.xxin.demo.restful.utils.JsonUtil;
import com.xxin.demo.restful.utils.RedisUtil;
import org.apache.tomcat.util.buf.HexUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.ldap.PagedResultsControl;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.UUID;


@Service
public class RestSessionService {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private RestSessionRepository sessionRepository;
    public RestSession getSession(int appId){
        RestSession session = null;
        if (redisUtil.hasKey("restful:session:"+appId)){
            session = JsonUtil.jsonToPojo( redisUtil.get("restful:session:"+appId).toString(), RestSession.class);
        }else{
            session = sessionRepository.queryByAppIdAndStatusEquals(appId, 1);
            if (session!=null){
                System.out.println(JsonUtil.objectToJson(session));
                redisUtil.set("restful:session:"+appId, JsonUtil.objectToJson(session));
            }
        }
        return session;
    }
    @Transactional
    public String createSession(int uId){
        RestSession restSession = new RestSession();
        restSession.setAppId(uId);
        restSession.setCreateTime(new Timestamp(System.currentTimeMillis()));
        restSession.setSessionId((int) System.currentTimeMillis());
        restSession.setStatus(1);
        restSession.setAccessToken(Encrypt.sha1(uId+""+Math.random()*100));
        sessionRepository.save(restSession);
        return restSession.getAccessToken();
    }
    @Transactional
    public void closeSession(int sessionId) {
        sessionRepository.updateBySessionId(sessionId,new Timestamp(System.currentTimeMillis()));
    }
}
