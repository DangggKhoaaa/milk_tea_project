package com.example.hudamilktea.api;

import com.example.hudamilktea.model.StaffManagement;
import com.example.hudamilktea.service.StaffManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SalaryRestController {
    @Autowired
    private StaffManagementService staffManagementService;

    @PostMapping("/calculate-salary")
    public ResponseEntity<Double> calculateSalary(@RequestBody StaffManagement staffManagement) {
        double totalSalary = staffManagementService.calculateAndSaveSalary(staffManagement);
        return ResponseEntity.ok(totalSalary);
    }
}
