package com.example.hudamilktea.api;

import com.example.hudamilktea.model.Product;
import com.example.hudamilktea.service.product.request.ProductRequest;
import com.example.hudamilktea.service.product.ProductService;
import com.example.hudamilktea.service.product.response.ProductListResponse;
import com.example.hudamilktea.service.productImage.ProductImageSaveRequest;
import com.example.hudamilktea.service.upload.UploadService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductRestController {
    private final UploadService uploadService;

    private final ProductService productService;
//@GetMapping
//public ResponseEntity<?>getAllProduct(ProductRequest request){
//    productService.findAll();
//    return ResponseEntity.ok(request);
//}

    @GetMapping
    public Page<ProductListResponse>findAll(@RequestParam(defaultValue = "") String search,
                                            Pageable pageable){
        return productService.findAllWithSearchAndPaging(search,pageable);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestParam(defaultValue = "") ProductRequest request, @RequestParam("fileInput") MultipartFile[] fileInput, Authentication authentication) throws IOException {
        ProductRequest newRequest = new ProductRequest();
        MultipartFile file = fileInput[0];
        if(fileInput != null && file.getSize() > 0) {
            List<ProductImageSaveRequest> media = uploadService.transferFiles(fileInput);
            newRequest.setImg((ProductImageSaveRequest) media);
        }
        Product product = productService.create(request);
        return ResponseEntity.ok(product);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody @Valid ProductRequest request, @PathVariable Long id){
        productService.update(request, id);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
