package com.example.hudamilktea.controller;


import com.example.hudamilktea.model.enums.Size;
import com.example.hudamilktea.repository.CategoryRepository;
import com.example.hudamilktea.repository.ProductRepository;
import com.example.hudamilktea.service.product.ProductService;
import com.example.hudamilktea.service.product.request.ProductRequest;
import com.example.hudamilktea.service.product.request.SelectOptionRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductService productService;


//    @GetMapping("/create")
//    public ModelAndView showFormCreate(){
//        return view;
//    }
@GetMapping
    public ModelAndView showAllProduct(){
    ModelAndView view = new ModelAndView("/products/product");
    return view;
}


}
