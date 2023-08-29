package com.example.hudamilktea.api;

import com.example.hudamilktea.model.Product;
import com.example.hudamilktea.model.ProductImg;
import com.example.hudamilktea.service.product.request.ProductRequest;
import com.example.hudamilktea.service.product.ProductService;
import com.example.hudamilktea.service.product.response.ProductListResponse;
import com.example.hudamilktea.service.productImage.ProductImageSaveRequest;
import com.example.hudamilktea.service.upload.UploadService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductRestController {
    private final UploadService uploadService;

    private final ProductService productService;

    @GetMapping
    public Page<ProductListResponse>findAll(@RequestParam(defaultValue = "") String search,@PageableDefault(size = 5)
                                            Pageable pageable){
        return productService.findAllWithSearchAndPaging(search,pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<Product> productOptional = productService.findById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@ModelAttribute ProductRequest request, MultipartFile img) throws IOException {
        if(img != null && img.getSize() > 0) {
            request.setImgUrl(uploadService.transferFile(img));
            productService.create(request);
            return ResponseEntity.ok(request);
        }

        return ResponseEntity.badRequest().build();
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
