package com.xxin.demo.springData.repository;

import com.xxin.demo.springData.domain.Employ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EmployJpaSpecificationExecutorRepository extends
        JpaRepository<Employ,Integer>,
        JpaSpecificationExecutor<Employ> {
}
