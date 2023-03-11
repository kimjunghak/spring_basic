package com.example.spring_basic.taco.web;

import com.example.spring_basic.taco.Ingredient;
import com.example.spring_basic.taco.Order;
import com.example.spring_basic.taco.Taco;
import com.example.spring_basic.taco.Users;
import com.example.spring_basic.taco.data.IngredientRepository;
import com.example.spring_basic.taco.data.TacoRepository;
import com.example.spring_basic.taco.data.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.spring_basic.taco.Ingredient.Type;

@Controller
@RequestMapping("/design")
@SessionAttributes("order")
@RequiredArgsConstructor
public class DesignTacoController {

  private final IngredientRepository ingredientRepo;
  private final TacoRepository designRepo;
  private final UserRepository userRepo;

  @ModelAttribute(name = "order")
  public Order order() {
    return new Order();
  }

  @ModelAttribute(name = "design")
  public Taco taco() {
    return new Taco();
  }

  @GetMapping
  public String showDesignForm(Model model, Principal principal) {
    List<Ingredient> ingredients = new ArrayList<>();
    ingredientRepo.findAll().forEach(ingredients::add);

    Type[] types = Type.values();
    for (Type type : types) {
      model.addAttribute(type.toString().toLowerCase(),
              filterByType(ingredients, type));
    }

    String username = principal.getName();
    Users users = userRepo.findByUsername(username);
    model.addAttribute("users", users);

    return "design";
  }

  @PostMapping
  public String processDesign(
          @Valid Taco design, Errors errors,
          @ModelAttribute Order order) {

    if (errors.hasErrors()) {
      return "design";
    }

    Taco saved = designRepo.save(design);
    order.addDesign(saved);

    return "redirect:/orders/current";
  }

  private List<Ingredient> filterByType(
          List<Ingredient> ingredients, Type type) {
    return ingredients
            .stream()
            .filter(x -> x.getType().equals(type))
            .collect(Collectors.toList());
  }
}
