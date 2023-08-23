package com.example.hudamilktea.service.DTO;

import com.example.hudamilktea.model.Product;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@AllArgsConstructor
@Data
public class CategoryRequest {

    private String name;
    private List<Product> productList;
}
