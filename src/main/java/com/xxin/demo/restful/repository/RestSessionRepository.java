package com.xxin.demo.restful.repository;

import com.xxin.demo.restful.entity.RestSession;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;

public interface RestSessionRepository extends JpaRepository<RestSession,Integer> {
    public RestSession queryByAppIdAndStatusEquals(int appId,int status);
    @Modifying
    @Query("UPDATE RestSession  set status = 0 , end_time = :endTime  where sessionId = :sessionId")
    public void updateBySessionId(@Param("sessionId") int sessionId, @Param("endTime")Timestamp endTime);
}
