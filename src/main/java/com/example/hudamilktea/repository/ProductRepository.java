package com.example.hudamilktea.repository;

import com.example.hudamilktea.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product , Long> {
}
