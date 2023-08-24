package com.example.hudamilktea.service;

import com.example.hudamilktea.model.Product;
import com.example.hudamilktea.repository.ProductRepository;
import com.example.hudamilktea.service.DTO.ProductRequest;
import com.example.hudamilktea.util.AppUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> findAll (){
        return productRepository.findAll();
    }

    public Product create(ProductRequest request) {
        return productRepository.save(AppUtils.mapper.map(request, Product.class));
    }
}
