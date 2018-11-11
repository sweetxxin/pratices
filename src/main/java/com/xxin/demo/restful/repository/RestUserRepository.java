package com.xxin.demo.restful.repository;

import com.xxin.demo.restful.entity.RestUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestUserRepository extends JpaRepository<RestUser,Integer> {
    public RestUser queryByAppId(int appId);
}
