package com.example.spring_basic.taco.data;

import com.example.spring_basic.taco.Users;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Users, Long> {

    Users findByUsername(String username);
}
