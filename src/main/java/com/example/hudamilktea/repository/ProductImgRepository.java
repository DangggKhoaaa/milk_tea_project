package com.example.hudamilktea.repository;

import com.example.hudamilktea.model.ProductImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImgRepository extends JpaRepository<ProductImg,String> {
}
