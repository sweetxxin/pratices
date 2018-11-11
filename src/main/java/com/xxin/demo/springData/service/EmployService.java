package com.xxin.demo.springData.service;

import com.xxin.demo.springData.domain.Employ;
import com.xxin.demo.springData.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.List;

@Service
public class EmployService {
    @Autowired
    EmployRepository employRepository;

    @Autowired
    EmployCrudRepository employCrudRepository;

    @Autowired
    EmployPagingAndSortingRepository employPagingAndSortingRepository;

    @Autowired
    EmployJpaRepository employJpaRepository;

    @Autowired
    EmployJpaSpecificationExecutorRepository specificationExecutorRepository;

    @Transactional
    public void updateAgeById(int id, int age){
        employRepository.updateAgeById(id,age );
    }

    @Transactional
    public void save(List<Employ> employs){
        employCrudRepository.saveAll(employs);
    }

    public void page(int index,int size){
        PageRequest pageable = new PageRequest(index,size);
        Page<Employ> pages =employPagingAndSortingRepository.findAll(pageable);
        System.out.println("总页数:"+pages.getTotalPages());
        System.out.println("总记录数:"+pages.getTotalElements());
        System.out.println("第几页:"+pages.getNumber());
        System.out.println("当前页集合:"+pages.getContent());
        System.out.println("当前页面记录数:"+pages.getNumberOfElements());
    }
    public void pageAndSort(int index,int size){
        Sort.Order order = new Sort.Order(Sort.Direction.DESC,"id" );
        Sort sort = new Sort(order);
        PageRequest pageable = new PageRequest(index,size,sort);
        Page<Employ> pages =employPagingAndSortingRepository.findAll(pageable);
        System.out.println("总页数:"+pages.getTotalPages());
        System.out.println("总记录数:"+pages.getTotalElements());
        System.out.println("第几页:"+pages.getNumber());
        System.out.println("当前页集合:"+pages.getContent());
        System.out.println("当前页面记录数:"+pages.getNumberOfElements());
    }
    public Employ findOne(Integer id){
      return   employJpaRepository.findById(id).get();
    }
    public boolean exists(Integer id){
        return employJpaRepository.existsById(id);
    }
    public void queryGtAge(int index,int size,Integer age){
        Sort.Order order = new Sort.Order(Sort.Direction.ASC,"id" );
        Sort sort = new Sort(order);
        PageRequest pageable = new PageRequest(index,size,sort);

        Specification<Employ>specification = new Specification<Employ>() {
            @Override
            public Predicate toPredicate(Root<Employ> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path path = root.get("age");
                return criteriaBuilder.gt(path, age);
            }
        };
        Page<Employ> pages =specificationExecutorRepository.findAll(specification,pageable);

        System.out.println("总页数:"+pages.getTotalPages());
        System.out.println("总记录数:"+pages.getTotalElements());
        System.out.println("第几页:"+pages.getNumber());
        System.out.println("当前页集合:"+pages.getContent());
        System.out.println("当前页面记录数:"+pages.getNumberOfElements());
    }
}
