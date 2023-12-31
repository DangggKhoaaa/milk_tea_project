package com.example.hudamilktea.controller;

import com.example.hudamilktea.model.Customer;
import com.example.hudamilktea.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/home")
@AllArgsConstructor
public class HomeController {

    private final CustomerRepository customerRepository;

    @GetMapping
    public String showHome (){
        return "customer/home";
    }

    @GetMapping("/products")
    public String showProduct (Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Customer customer = customerRepository.findCustomerByUsernameIgnoreCase(username);
        model.addAttribute("customer", customer);
        return "customer/product";
    }

    @GetMapping("/cart")
    public String showCart (Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Customer customer = customerRepository.findCustomerByUsernameIgnoreCase(username);
        model.addAttribute("customer", customer);
        return "customer/cart";
    }

}
