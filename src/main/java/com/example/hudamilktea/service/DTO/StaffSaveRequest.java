package com.example.hudamilktea.service.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class StaffSaveRequest {
    private String fullName;
    private String staffName;
    private String password;
    private String email;
    private String phone;
    private String age;
    private LocationRegionRequest locationRegion;


}
