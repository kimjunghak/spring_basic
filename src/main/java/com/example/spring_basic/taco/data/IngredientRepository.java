package com.example.spring_basic.taco.data;

import com.example.spring_basic.taco.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {

}
