package com.xxin.demo.springData.repository;

import com.xxin.demo.springData.domain.Employ;
import org.springframework.data.repository.CrudRepository;

public interface EmployCrudRepository extends CrudRepository<Employ,Integer> {

}
