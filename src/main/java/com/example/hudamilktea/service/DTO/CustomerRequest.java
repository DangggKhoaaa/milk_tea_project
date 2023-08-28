package com.example.hudamilktea.service.DTO;

import com.example.hudamilktea.model.LocationRegion;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerRequest {
    private String username;

    private String password;

    private String fullName;

    private String phone;

    private LocationRegion locationRegion;
}
