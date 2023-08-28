package com.example.hudamilktea.repository;

import com.example.hudamilktea.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
    Optional<Staff> findByStaffNameIgnoreCaseOrEmailIgnoreCaseOrPhone(String staffName, String email, String phone);

    Staff existsByStaffName(String staffName);



    boolean existsByEmailIgnoreCase(String email);

    boolean existsByPhone(String phone);
    Optional<Staff> findById(Long id);

}
