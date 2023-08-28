package com.example.hudamilktea.service;

import org.springframework.stereotype.Service;

@Service
public class SalaryService {
    public double calculateSalary(double workedHours) {
        double salary = workedHours * 15000;
        return salary;
    }
}
