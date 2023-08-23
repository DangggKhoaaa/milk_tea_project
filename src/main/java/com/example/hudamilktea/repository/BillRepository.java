package com.example.hudamilktea.repository;

import com.example.hudamilktea.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Long> {
}
