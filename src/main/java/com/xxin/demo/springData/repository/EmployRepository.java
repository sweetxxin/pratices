package com.xxin.demo.springData.repository;

import com.xxin.demo.springData.domain.Employ;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface EmployRepository extends Repository<Employ,Integer> {
    public Employ findByName(String name);
    // name ...* age<?
    public List<Employ> findByNameEndingWithAndAgeIsLessThan(String name,int age);

    @Query("SELECT o from Employ o where id = (select max(id) from Employ t1)")
    public Employ findMaxId();

    @Query("select o from Employ o where o.name = ?1 and o.age = ?2")
    public List<Employ> findByParams1(String name,int age);

    @Query("SELECT o from Employ o where o.name = :name and o.age = :age")
    public List<Employ> findByParams2(@Param("name") String name,@Param("age") int age);

    @Query("SELECT o from Employ o where o.name like %?1%")
    public List<Employ> findNameLike(String name);

    @Query("SELECT o from Employ o where o.name like %:name%")
    public List<Employ> findNameLike2(@Param("name") String name);

    @Query(nativeQuery = true,value = "select count(*) from employ")
    public int count();

    @Modifying
    @Query("update Employ o set o.age = :age where o.id = :id")
    public void updateAgeById(@Param("id")int id,@Param("age") int age);


}
