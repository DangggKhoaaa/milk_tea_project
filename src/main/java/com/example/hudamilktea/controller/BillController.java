package com.example.hudamilktea.controller;

import com.example.hudamilktea.service.DTO.BillRequest;
import com.example.hudamilktea.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bills")
@AllArgsConstructor
public class BillController {
    private final ProductService productService;

    @GetMapping
    public String show(Model model) {
        model.addAttribute("bill", new BillRequest());
        model.addAttribute("products", productService.findAll());
        return "bill";
    }
}
