package com.example.hudamilktea.service.productImage;

import com.example.hudamilktea.model.enums.FileType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ProductImageSaveRequest {
    private String url;
    private FileType fileType;
}
