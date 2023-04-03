package com.example.spring_basic.taco.data;


import com.example.spring_basic.taco.Order;
import com.example.spring_basic.taco.Users;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> findByUsersOrderByPlacedAtDesc(Users users, Pageable pageable);
}
