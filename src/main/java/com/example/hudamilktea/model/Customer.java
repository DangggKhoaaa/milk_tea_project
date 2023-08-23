package com.example.hudamilktea.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String fullName;

    private String phone;

    @OneToOne
    @JoinColumn(name = "location_region_id")
    private LocationRegion locationRegion;

    @OneToMany(mappedBy = "customer")
    private List<Bill> bills;
}
