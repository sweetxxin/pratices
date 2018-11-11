package com.xxin.demo.restful.service;

import com.xxin.demo.restful.entity.RestUser;
import com.xxin.demo.restful.repository.RestUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RestUserService {
    @Autowired
    private RestUserRepository userRepository;
    public RestUser getUser(int appId){
        return userRepository.queryByAppId(appId);
    }
    @Transactional
    public void createUser(RestUser user){
        userRepository.save(user);
    }
}
