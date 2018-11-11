package com.xxin.demo.springData.repository;

import com.xxin.demo.springData.domain.Employ;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployJpaRepository extends JpaRepository<Employ,Integer> {
}
