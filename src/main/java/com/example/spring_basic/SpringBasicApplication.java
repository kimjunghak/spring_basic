package com.example.spring_basic;

import com.example.spring_basic.taco.Ingredient;
import com.example.spring_basic.taco.data.IngredientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;

import static com.example.spring_basic.taco.Ingredient.Type;

@SpringBootApplication
public class SpringBasicApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBasicApplication.class, args);
    }

    @Bean
//    @Profile({"dev", "qa"})
    @Profile("!prod") //prod 프로파일이 활성화되지 않을경우
    public CommandLineRunner dataLoader(IngredientRepository repo) {
        return (args) -> {
            ArrayList<Ingredient> ingredients = new ArrayList<>();
            ingredients.add(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
            ingredients.add(new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
            ingredients.add(new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
            ingredients.add(new Ingredient("CARN", "Carnitas", Type.PROTEIN));
            ingredients.add(new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
            ingredients.add(new Ingredient("LETC", "Lettuce", Type.VEGGIES));
            ingredients.add(new Ingredient("CHED", "Cheddar", Type.CHEESE));
            ingredients.add(new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
            ingredients.add(new Ingredient("SLSA", "Salsa", Type.SAUCE));
            ingredients.add(new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
            repo.saveAll(ingredients);
        };
    }

}
