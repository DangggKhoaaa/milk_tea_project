package com.example.hudamilktea.repository;

import com.example.hudamilktea.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer , Long> {
}
