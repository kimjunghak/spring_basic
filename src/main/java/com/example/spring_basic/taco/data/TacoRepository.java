package com.example.spring_basic.taco.data;


import com.example.spring_basic.taco.Taco;
import org.springframework.data.repository.CrudRepository;

public interface TacoRepository extends CrudRepository<Taco, Long> {

}
