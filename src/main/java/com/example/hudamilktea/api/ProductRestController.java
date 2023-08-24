package com.example.hudamilktea.api;

import com.example.hudamilktea.model.Product;
import com.example.hudamilktea.service.DTO.ProductRequest;
import com.example.hudamilktea.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductRestController {

    private final ProductService productService;

    @GetMapping
    public List<Product> findAll() {
        return productService.findAll();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProductRequest request) {
        productService.create(request);
        return ResponseEntity.ok(request);
    }
}
