package com.xxin.demo.rabbitMq.repository;

import com.xxin.demo.rabbitMq.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders,Integer> {

}
