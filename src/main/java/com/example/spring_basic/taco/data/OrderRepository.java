package com.example.spring_basic.taco.data;


import com.example.spring_basic.taco.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {

}
