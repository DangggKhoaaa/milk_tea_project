package com.example.hudamilktea.service.product;

import com.example.hudamilktea.model.Product;
import com.example.hudamilktea.model.ProductImg;
import com.example.hudamilktea.repository.ProductRepository;
import com.example.hudamilktea.service.product.request.ProductRequest;
import com.example.hudamilktea.service.product.response.ProductListResponse;
import com.example.hudamilktea.service.upload.UploadService;
import com.example.hudamilktea.util.AppUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private final UploadService uploadService;
    private final ProductRepository productRepository;

    public Page<ProductListResponse> findAllWithSearchAndPaging(String search, Pageable pageable) {
        search = "%" + search + "%";
        Page<ProductListResponse> ada = productRepository
                .searchEverything(search, pageable).map(product -> {
                    var response = AppUtils.mapper.map(product, ProductListResponse.class);
                    return response;
                });

        return productRepository
                .searchEverything(search, pageable).map(product -> {
                    var response = AppUtils.mapper.map(product, ProductListResponse.class);
                    return response;
                });
    }

    public Product create(ProductRequest request) {
        Product newProduct = AppUtils.mapper.map(request, Product.class);

        productRepository.save(newProduct);
        return newProduct;
    }

    public void update(ProductRequest request, Long id) {
        Product newProduct = AppUtils.mapper.map(request, Product.class);
        newProduct.setId(id);
        productRepository.save(newProduct);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }
    public List<Product> findAll(){
        return productRepository.findAll();
    }
}
