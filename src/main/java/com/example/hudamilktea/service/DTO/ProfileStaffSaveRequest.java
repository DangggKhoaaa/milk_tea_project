package com.example.hudamilktea.service.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ProfileStaffSaveRequest {
    private String fullName;
    private String email;
    private String phone;
    private String age;
    private LocationRegionRequest locationRegion;
}
