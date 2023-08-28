package com.example.hudamilktea.controller;

import com.example.hudamilktea.model.Customer;
import com.example.hudamilktea.repository.CustomerRepository;
import com.example.hudamilktea.service.CustomerService;
import com.example.hudamilktea.service.DTO.CustomerRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class AuthController {
    private CustomerService customerService;
    private CustomerRepository customerRepository;

    @GetMapping("/login")
    public String showLogin(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Customer customer = customerRepository.findCustomerByUsernameIgnoreCase(username);
        model.addAttribute("customer", customer);
        return "customer/login";
    }

//    @PostMapping("/login")
//    public String login(Model model) {

//        return "customer/home";
//    }

    @GetMapping("/register")
    public String showRegister(Model model){
        CustomerRequest customer = new CustomerRequest();
        model.addAttribute("customer",customer);
        return "customer/register";
    }

    @PostMapping("/register")
    public String registration(@Valid @ModelAttribute("customer") CustomerRequest request, BindingResult result){

//        authService.checkRegister(request, result);
//
//        if(result.hasErrors()){
//            return "/register";
//        }

        customerService.create(request);
        return "redirect:/register?success";
    }


}
