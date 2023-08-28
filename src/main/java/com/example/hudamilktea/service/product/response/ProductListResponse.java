package com.example.hudamilktea.service.product.response;

import com.example.hudamilktea.model.ProductImg;
import com.example.hudamilktea.service.productImage.ProductImageSaveRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@AllArgsConstructor
@Data
@NoArgsConstructor
public class ProductListResponse {
    private Long id;
    private String name;
    private BigDecimal price;
    private ProductImageSaveRequest img;
}
