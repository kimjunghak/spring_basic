package com.example.spring_basic.taco.web;

import com.example.spring_basic.taco.Order;
import com.example.spring_basic.taco.Users;
import com.example.spring_basic.taco.data.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
@RequiredArgsConstructor
public class OrderController {
  
  private final OrderRepository orderRepo;

  @GetMapping("/current")
  public String orderForm(@AuthenticationPrincipal Users users,
                          @ModelAttribute Order order) {
    if (order.getDeliveryName() == null) {
      order.setDeliveryName(users.getFullname());
    }
    if (order.getDeliveryStreet() == null) {
      order.setDeliveryStreet(users.getStreet());
    }
    if (order.getDeliveryCity() == null) {
      order.setDeliveryCity(users.getCity());
    }
    if (order.getDeliveryState() == null) {
      order.setDeliveryState(users.getState());
    }
    if (order.getDeliveryZip() == null) {
      order.setDeliveryZip(users.getZip());
    }

    return "orderForm";
  }

  @PostMapping
  public String processOrder(@Valid Order order, Errors errors,
                             SessionStatus sessionStatus,
//                             Authentication authentication,
                             @AuthenticationPrincipal Users users) {
    if (errors.hasErrors()) {
      return "orderForm";
    }

//    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//    Users users = (Users) authentication.getPrincipal();
    order.setUsers(users);
    orderRepo.save(order);
    sessionStatus.setComplete();
    
    return "redirect:/";
  }

}
