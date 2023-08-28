package com.example.hudamilktea.repository;

import com.example.hudamilktea.model.Staff;
import com.example.hudamilktea.model.StaffManagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffManagementRepository extends JpaRepository<StaffManagement, Long> {
    StaffManagement findByStaffAndAttendanceEndTimeIsNull(Staff staff);
}
