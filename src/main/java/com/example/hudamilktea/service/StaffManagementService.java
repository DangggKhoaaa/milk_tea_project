package com.example.hudamilktea.service;

import com.example.hudamilktea.model.Staff;
import com.example.hudamilktea.model.StaffManagement;
import com.example.hudamilktea.repository.StaffManagementRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
@AllArgsConstructor
public class StaffManagementService {
    @Autowired
    private final StaffManagementRepository staffManagementRepository;
    private static final double HOURLY_WAGE = 15000;
    private static final int WORKING_HOURS_PER_SHIFT = 5;

    public void checkIn(Staff staff) {
        StaffManagement staffManagement = new StaffManagement();
        staffManagement.setStaff(staff);
        staffManagement.setAttendanceStartTime(LocalDateTime.now());
        staffManagementRepository.save(staffManagement);
    }
    public void checkOut(Staff staff) {
        StaffManagement staffManagement = staffManagementRepository.findByStaffAndAttendanceEndTimeIsNull(staff);
        if (staffManagement != null) {
            staffManagement.setAttendanceEndTime(LocalDateTime.now());
            double salary = calculateSalary(staffManagement);
            staffManagement.setSalary(salary);
            staffManagementRepository.save(staffManagement);
        }
    }
    private double calculateSalary(StaffManagement staffManagement) {
        LocalDateTime startTime = staffManagement.getAttendanceStartTime();
        LocalDateTime endTime = staffManagement.getAttendanceEndTime();
        long workingHours = ChronoUnit.HOURS.between(startTime, endTime);
        double totalSalary = workingHours * HOURLY_WAGE;

        int shiftsWorked = (int) Math.ceil((double) workingHours / WORKING_HOURS_PER_SHIFT);
        return totalSalary - (shiftsWorked - 1) * WORKING_HOURS_PER_SHIFT * HOURLY_WAGE;
    }
    public double calculateAndSaveSalary(StaffManagement staffManagement) {
        LocalDateTime startTime = staffManagement.getAttendanceStartTime();
        LocalDateTime endTime = staffManagement.getAttendanceEndTime();

        Duration duration = Duration.between(startTime, endTime);
        long hoursWorked = duration.toHours();

        double hourlyRate = 15000;
        double totalSalary = hoursWorked * hourlyRate;

        staffManagement.setSalary(totalSalary);
        staffManagementRepository.save(staffManagement);

        return totalSalary;
    }
}
