package com.example.hudamilktea.repository;

import com.example.hudamilktea.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer , Long> {
    Optional<Customer> findCustomerByUsername(String username);
    Customer findCustomerByUsernameIgnoreCase(String username);
}
