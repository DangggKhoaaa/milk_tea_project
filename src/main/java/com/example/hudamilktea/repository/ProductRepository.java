package com.example.hudamilktea.repository;

import com.example.hudamilktea.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface ProductRepository extends JpaRepository<Product , Long> {
    @Query(value = "select p from Product  p where " +
            "(p.name like :search)")
    Page<Product> searchEverything(String search, Pageable pageable);
    Page<Product> findAllByNameContainingOrPriceContainingOrCategory_NameContaining(String name, BigDecimal price, String category_name, Pageable pageable);
}
