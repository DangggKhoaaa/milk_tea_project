package com.example.hudamilktea.controller;

import com.example.hudamilktea.model.Customer;
import com.example.hudamilktea.repository.CustomerRepository;
import com.example.hudamilktea.service.DTO.BillRequest;
import com.example.hudamilktea.service.product.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bills")
@AllArgsConstructor
public class BillController {
    private final ProductService productService;

    private final CustomerRepository customerRepository;

    @GetMapping
    public String show(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Customer customer = customerRepository.findCustomerByUsernameIgnoreCase(username);
        model.addAttribute("customer", customer);
        model.addAttribute("bill", new BillRequest());
        model.addAttribute("products", productService.findAll());
        return "/customer/cart";
    }
}
