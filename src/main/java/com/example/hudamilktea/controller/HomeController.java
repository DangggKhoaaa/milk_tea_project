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

//    @GetMapping("/register")
//    public String showRegister (){
//        return "customer/register";
//    }
//
//    @GetMapping("/login")
//    public String showLogin (){
//        return "customer/login";
//    }

    @GetMapping("/products")
    public String showProduct (){
        return "customer/product";
    }

    @GetMapping("/cart")
    public String showCart (){
        return "customer/cart";
    }

}
