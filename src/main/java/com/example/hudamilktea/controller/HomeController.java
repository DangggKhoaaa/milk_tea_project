package com.example.hudamilktea.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping
    public String showHome (){
        return "home";
    }

    @GetMapping("/register")
    public String showRegister (){
        return "customer/register";
    }

    @GetMapping("/login")
    public String showLogin (){
        return "customer/login";
    }

    @GetMapping("/products")
    public String showProduct (){
        return "customer/product";
    }

    @GetMapping("/cart")
    public String showCart (){
        return "customer/cart";
    }

    @GetMapping("/reg")
    public String showRe (){
        return "register";
    }
}
