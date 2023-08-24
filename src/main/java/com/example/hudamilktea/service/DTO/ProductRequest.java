package com.example.hudamilktea.service.DTO;

import com.example.hudamilktea.model.Category;
import com.example.hudamilktea.model.enums.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ProductRequest {
    private String name;
    private String price;
    private Size size;
    private Category category;
}
