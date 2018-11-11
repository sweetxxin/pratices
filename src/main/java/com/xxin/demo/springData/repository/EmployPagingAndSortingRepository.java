package com.xxin.demo.springData.repository;

import com.xxin.demo.springData.domain.Employ;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EmployPagingAndSortingRepository extends PagingAndSortingRepository<Employ,Integer> {
}
