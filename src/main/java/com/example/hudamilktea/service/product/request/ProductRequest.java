package com.example.hudamilktea.service.product.request;

import com.example.hudamilktea.model.Category;
import com.example.hudamilktea.model.enums.FileType;
import com.example.hudamilktea.model.enums.Size;
import com.example.hudamilktea.service.productImage.ProductImageSaveRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ProductRequest {
    private String name;
    private String price;
    private String imgUrl;
//    private String url;
//    private FileType fileType;
//    private ProductImageSaveRequest img;
}
