package com.example.hudamilktea.service.product.request;

import com.example.hudamilktea.model.Category;
import com.example.hudamilktea.model.enums.FileType;
import com.example.hudamilktea.model.enums.Size;
import com.example.hudamilktea.service.productImage.ProductImageSaveRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ProductRequest {
    private String name;
    private String price;
    private ProductImageSaveRequest img;
}
